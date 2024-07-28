package coursematch.data_access_objects;

import coursematch.database_manager.CourseMatchDB;
import coursematch.entities.Account;
import coursematch.utils.Random;
import java.sql.*;
import java.util.ArrayList;

public class AccountDAO {

    private final CourseMatchDB connection;

    public AccountDAO() throws SQLException, ClassNotFoundException {
        this.connection = new CourseMatchDB();
    }

    private boolean checkAccountID(int account_id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT user_id FROM coursematchdb.account WHERE user_id =?";

        PreparedStatement ps = connection.getConnection().prepareStatement(sql);
        ps.setInt(1, account_id);
        ResultSet rs = ps.executeQuery();

        return rs.next();
    }//end

    public final boolean addAccount(Account account, String user_id) throws SQLException, ClassNotFoundException {

        Random random = new Random();
        int account_id = random.randomizeTableID();

        while (checkAccountID(account_id)) {
            account_id = random.randomizeTableID();
        }

        String sql = "INSERT INTO coursematchdb.account "
                + "VALUES(?,?,?,?)";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, account_id);
            ps.setString(2, account.getUsername());
            ps.setString(3, account.getPassword());
            ps.setString(4, user_id);
            ps.executeUpdate();
            ps.close();
        }
        return true;
    }//end

    public final boolean validateLogin(Account account) throws SQLException, ClassNotFoundException {

        String sql = "SELECT username, password FROM coursematchdb.account"
                + " WHERE username=? AND password=? ";
        PreparedStatement ps = connection.getConnection().prepareStatement(sql);
        System.out.println(account.getUsername());
        System.out.println(account.getPassword());

        ps.setString(1, account.getUsername());
        ps.setString(2, account.getPassword());

        ResultSet rs = ps.executeQuery();

        return rs.next();
    }//end

    public String getAccountId(String username, String password) throws SQLException, ClassNotFoundException {

        String sql = "SELECT user_id FROM coursematchdb.account "
                + "WHERE username =? AND password=? ";

        String account_id;
        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            account_id = null;
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    account_id = rs.getString("User_Id");
                }
            }
        }
        return account_id;
    }//end

    public ArrayList<String> getAccountHolderDetails(String id_number) throws SQLException, ClassNotFoundException {

        String sql = "SELECT first_name, last_name FROM coursematchdb.user "
                + "WHERE id_number =? ";

        ArrayList<String> account_holder_details = new ArrayList<>();

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {

            ps.setString(1, id_number);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    account_holder_details.add(rs.getString("First_Name"));
                    account_holder_details.add(rs.getString("Last_Name"));
                }
            }
            ps.close();
        }

        return account_holder_details;
    }//end

}

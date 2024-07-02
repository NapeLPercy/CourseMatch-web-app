package Data_Access_Objects;

import Database_Manager.CourseMatchDB;
import Entities.Account;
import Entities.User;
import java.sql.*;

public class UserDAO {

    private final CourseMatchDB connection;

    public UserDAO() throws SQLException, ClassNotFoundException {
        this.connection = new CourseMatchDB();
    }

    public final void addUser(User user) throws SQLException, ClassNotFoundException {

        AccountDAO account_dao = new AccountDAO();

        Account account = new Account(user.getAccount().getUsername(), user.getAccount().getPassword());

        String sql = "INSERT INTO coursematchdb.user "
                + "VALUES(?,?,?,?)";
        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setString(1, user.getId_number());
            ps.setString(2, user.getName());
            ps.setString(3, user.getSurname());
            ps.setString(4, user.getGender());
            ps.executeUpdate();

            ps.close();
        }

        account_dao.addAccount(account, user.getId_number());

    }//end

}

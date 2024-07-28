package coursematch.data_access_objects;

import coursematch.database_manager.CourseMatchDB;
import coursematch.entities.Account;
import coursematch.entities.User;
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
            ps.setString(1, user.getIdNumber());
            ps.setString(2, user.getName());
            ps.setString(3, user.getSurname());
            ps.setString(4, user.getGender());
            ps.executeUpdate();

            ps.close();
        }

        account_dao.addAccount(account, user.getIdNumber());

    }//end

}

package Database_Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CourseMatchDB {

    private final Connection connection;

    public CourseMatchDB() throws SQLException, ClassNotFoundException {
        connection = this.getConnection();
    }

    public final Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost/coursematchdb?useSSL=false&&allowPublicKeyRetrieval=true",
                "perc",
                "percyy"
        );

    }
}

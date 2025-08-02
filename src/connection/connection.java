package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
    private static final String URL = "jdbc:mysql://localhost:3306/1gs221ds";
    private static final String USER = "root";
    private static final String PASSWORD = "Programming/07";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established successfully.");
            return connection;
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }

}

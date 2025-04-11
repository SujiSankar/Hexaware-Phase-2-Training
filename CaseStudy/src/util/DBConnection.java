package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Static method that takes a connection string and returns a database connection
    public static Connection getConnection(String connectionString) {
        if (connectionString == null || connectionString.isEmpty()) {
            System.err.println("Connection string is null or empty!");
            return null;
        }

        try {
            // Load JDBC driver (if necessary)
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(connectionString);
            System.out.println("Database connected!");
            return conn;

        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}

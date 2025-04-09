package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    // Method to get a new connection each time
    public static Connection getConnection() {
        try {
            // Load database properties
            Properties properties = Propertyutil.loadProperties();
            if (properties == null) {
                System.err.println(" Failed to load database properties.");
                return null;
            }

            // Extract database properties
            String dbUrl = properties.getProperty("db.url");
            String dbUser = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");

            // Ensure properties are not null
            if (dbUrl == null || dbUser == null || dbPassword == null) {
                System.err.println(" Database properties are missing!");
                return null;
            }

            // Establish and return a new connection
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println(" Database connected successfully!");
            return conn;

        } catch (SQLException e) {
            System.err.println(" Database connection error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}

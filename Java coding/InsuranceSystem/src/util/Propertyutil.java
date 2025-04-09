package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Propertyutil {

    // Method to load properties file and return the connection string
    public static String getConnectionString(String fileName) {
        Properties properties = new Properties();

        // Load the properties file from resources
        try (InputStream input = Propertyutil.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                System.err.println("Unable to find " + fileName);
                return null;
            }
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Failed to load properties file " + fileName);
            e.printStackTrace();
            return null;
        }

        // Extract database properties
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        if (url == null || user == null || password == null) {
            System.err.println("Missing required database properties in " + fileName);
            return null;
        }

        // Return the formatted connection string
        return url + "?user=" + user + "&password=" + password;
    }
}

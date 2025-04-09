package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Propertyutil {
    public static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = Propertyutil.class.getClassLoader().getResourceAsStream("dbconfig.properties")) {
            if (input == null) {
                System.err.println("Unable to find dbconfig.properties in resources.");
                return null;
            }
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Failed to load database properties.");
            e.printStackTrace();
        }
        return properties;
    }

    public static String getPropertyString() {
        Properties properties = loadProperties();
        if (properties == null) return null;
        
        return properties.getProperty("db.url") + "?user=" + properties.getProperty("db.username") +
                "&password=" + properties.getProperty("db.password");
    }
}


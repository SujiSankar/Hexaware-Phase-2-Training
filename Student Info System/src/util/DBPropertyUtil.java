package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil { 
    // This method takes the file name which contains 
    // the database URL, username, and password as an argument 
    // and returns a connection string
    public static String getConnectionString(String fileName) throws IOException { 
        String connStr = null;
        Properties props = new Properties();
        
        // Load the properties file
        try (FileInputStream fis = new FileInputStream(fileName)) {
            props.load(fis); 
        }

        // Retrieve the database URL, username, and password from the properties file
        String url = props.getProperty("db.url"); 
        String username = props.getProperty("db.username"); 
        String password = props.getProperty("db.password"); 
        
        // Construct the connection string using the db.url format
        if (url != null && username != null && password != null) {
            connStr = url + "?user=" + username + "&password=" + password;
        } else {
            throw new IOException("Missing database connection properties.");
        }
        
        return connStr; 
    }
}

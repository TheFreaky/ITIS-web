package ru.itis.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbWrapper {
    private static Connection conn;

    private DbWrapper() {
        conn = getLocalConnect();
    }

    public static Connection getConnection() {
        Connection localInstance = conn;
        if (localInstance == null) {
            synchronized (DbWrapper.class) {
                localInstance = conn;
                if (localInstance == null) {
                    new DbWrapper();
                    return conn;
                }
            }
        }
        return localInstance;
    }

    private Connection getLocalConnect() {
        try {
            Properties prop = new Properties();
            prop.load(getClass().getClassLoader().getResourceAsStream("db.properties"));

            final String DRIVER = prop.getProperty("driver");
            final String CONNECTION_URI = prop.getProperty("url");
            final String LOGIN = prop.getProperty("login");
            final String PASSWORD = prop.getProperty("password");

            Class.forName(DRIVER);
            return DriverManager.getConnection(CONNECTION_URI, LOGIN, PASSWORD);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

package ru.itis.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        final String DRIVER = "org.postgresql.Driver";
        final String CONNECTION_URI = "jdbc:postgresql://localhost:5432/gym-app";
        final String LOGIN = "postgres";
        final String PASSWORD = "lolxaxlol";

        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(CONNECTION_URI, LOGIN, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }
}

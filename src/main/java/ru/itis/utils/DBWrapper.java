package ru.itis.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWrapper {
    private static Connection conn;

    private DBWrapper() {
        conn = getLocalConnect();
    }

    public static Connection getConnection(){
        Connection localInstance = conn;
        if (localInstance == null) {
            synchronized (DBWrapper.class) {
                localInstance = conn;
                if (localInstance == null) {
                    new DBWrapper();
                    return conn;
                }
            }
        }
        return localInstance;
    }

    private Connection getLocalConnect() {
        final String DRIVER = "org.postgresql.Driver";
        final String CONNECTION_URI = "jdbc:postgresql://localhost:5432/postgres";
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

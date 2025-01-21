package com.doceria.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String USER = "docker";
    private static final String PASSWORD = "docker";
    private static final String URL_DB = "jdbc:postgresql://localhost:5432/doceria";

    public static Connection getConnection() {
        Connection conn = null; // a default null connection
        try {
            conn = DriverManager.getConnection(DBConnection.URL_DB, DBConnection.USER, DBConnection.PASSWORD); // server password
            if (conn != null) {
                System.out.println("Connected to database #1");
            }
        } catch (SQLException e) {
            System.out.println("Error when connecting...: " + e); //e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return conn;
    }
}
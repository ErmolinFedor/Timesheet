package com.ermolin.timesheet.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCPostgreeConnection {
    private static String DB_URL = null;
    private static String USER = null;
    private static String PASS = null;
    private static Properties properties = null;
    static {
        properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream("properties.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DB_URL = properties.getProperty("URL");
        USER = properties.getProperty("USER");
        PASS = properties.getProperty("PASS");
    }

    public JDBCPostgreeConnection() {}

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
           // System.out.println("PostgreSQL JDBC Driver successfully connected");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();//подумать что делать если не те данные для подключеиния
        }
        return connection;
    }

}

package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

//    private static Connection singleInstance = null;
    public static Connection connect() throws SQLException
     {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/FlipFitDB";
            Connection connection = DriverManager.getConnection(url,"root","");
//            System.out.println("Database Connected");
            return connection;
        } catch (Exception e) {
            System.out.println("Could not connect to DB: " + e.getMessage());
            return null;
        }
    }
}

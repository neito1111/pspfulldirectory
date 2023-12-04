package com.example.demo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String HOST = "jdbc:postgresql://localhost:5432/strweb4.1";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "saipiskursach";

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}

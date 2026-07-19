package com.example.BD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {

    private static final String url = "jdbc:postgresql://localhost:5432/To-Do_Tasks";
    private static final String user = "postgres";
    private static final String password = "postgres";

    private ConnectionBD(){
        throw new UnsupportedOperationException("Это утилитный класс, нельзя создавать экземпляры");
    }


    public static Connection getConnection() throws SQLException {
        Connection connect = DriverManager.getConnection(url,user,password);
        return connect;
    }

}

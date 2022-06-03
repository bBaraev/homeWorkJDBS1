package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    public static final String URL ="jdbc:postgresql://localhost:5432/postgres";
    public static final String UserName ="postgres";
    public static final String PASSWORD ="postgres";
    public static Connection connection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,UserName,PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}


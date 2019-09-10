package com.onlineBlog.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author: AbbyYuan
 * @date:10/9/19 1:20 PM
 * @description:
 */
public class DBConnection {
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5433/onlineBlog";

    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "123";

    static Connection dbConnection = null;

    public static PreparedStatement prepare(String stm) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            Connection dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(stm);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return  preparedStatement;

    }

    private static Connection getDBConnection() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());

            dbConnection = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);

            dbConnection.setAutoCommit(false);

            return dbConnection;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Connection problem");
        return null;
    }

}


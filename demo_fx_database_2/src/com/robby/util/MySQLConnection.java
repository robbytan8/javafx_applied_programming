package com.robby.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Robby Tan
 */
public class MySQLConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/demofx2020";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        connection.setAutoCommit(false);
        return connection;
    }
}

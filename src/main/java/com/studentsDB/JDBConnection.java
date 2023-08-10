package com.studentsDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/students_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection is established: " + connection.isValid(0));
        } catch (SQLException e) {
            System.out.println("ERROR " + e.getMessage());
        }
        return connection;
    }
}

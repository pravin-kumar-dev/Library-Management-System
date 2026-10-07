package com.pravin.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class DBConnection {

        private static final String URL =
                "jdbc:sqlserver://localhost:1433;databaseName=LibraryDB;encrypt=true;trustServerCertificate=true";

        private static final String USERNAME = "YOUR_USERNAME";
        private static final String PASSWORD = "YOUR_PASSWORD";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }

        public static void main(String[] args) {

            try {
                Connection connection = getConnection();

                System.out.println("Database Connected Successfully!");

                connection.close();

            } catch (SQLException e) {
                System.out.println("Database Connection Failed!");
                e.printStackTrace();
            }
        }
    }


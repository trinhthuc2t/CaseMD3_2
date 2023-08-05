package com.example.casemd3_3.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    private final String URL = "jdbc:mysql://localhost:3306/wish?useSSL=false";
    private final String USER = "root";
    private final String PASS = "123456";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

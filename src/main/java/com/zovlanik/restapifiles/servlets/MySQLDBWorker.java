package com.zovlanik.restapifiles.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDBWorker {
    private static final String URL = "jdbc:mysql://localhost:3306/restapi";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;

    public MySQLDBWorker()  {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }

    }

    public Connection getConnection() {
        return connection;
    }
}

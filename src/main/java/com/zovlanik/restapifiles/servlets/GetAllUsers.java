package com.zovlanik.restapifiles.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetAllUsers extends HttpServlet {
    static final String GET_ALL_USERS = "SELECT * FROM users";
    private final MySQLDBWorker dbWorker = new MySQLDBWorker();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String title = "Users Info";
        String docType = "<!DOCTYPE html>";

        try {
            Connection connection = dbWorker.getConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);

            writer.println(docType + "<html><head><title>" + title + "</title></head><body>");
            writer.println("<h1>USERS LIST</h1>");
            writer.println("<br/>");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);

                writer.println("ID: " + id);
                writer.println("Name: " + name + "<br/>");

                writer.println("<br/>");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        writer.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

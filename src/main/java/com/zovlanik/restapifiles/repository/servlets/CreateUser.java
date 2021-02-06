package com.zovlanik.restapifiles.repository.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class CreateUser extends HttpServlet {
    private static final String CREATE = "INSERT INTO users (user_id, name) VALUES (?, ?);";
    private final MySQLDBWorker dbWorker = new MySQLDBWorker();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String title = "Create User";
        String docType = "<!DOCTYPE html>";

        try {
            PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(CREATE);
            preparedStatement.setInt(1, Integer.parseInt(request.getParameter("user_id")));
            preparedStatement.setString(2, request.getParameter("name"));

            preparedStatement.execute();


            writer.println(docType + "<html><head><title>" + title + "</title></head><body>");
            writer.println("<h1>Created User:</h1>");
            writer.println("User name = " + request.getParameter("name"));
            writer.println("<br/>");

            dbWorker.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        writer.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

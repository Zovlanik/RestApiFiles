package com.zovlanik.restapifiles.view.servlets;

import com.zovlanik.restapifiles.controller.UserController;
import com.zovlanik.restapifiles.model.User;

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
import java.util.List;

public class GetAllUsers extends HttpServlet {
    UserController userController = new UserController();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String title = "Users Info";
        String docType = "<!DOCTYPE html>";

        try {
            List<User> users = userController.getAll();

            writer.println(docType + "<html><head><title>" + title + "</title></head><body>");
            writer.println("<button formaction=\"home.html\">Home</button>");
            writer.println("<h1>USERS LIST</h1>");
            writer.println("<br/>");

            for(User user : users) {
                writer.println("ID: " + user.getId());
                writer.println("Name: " + user.getUsername() + "<br/>");

                writer.println("<br/>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

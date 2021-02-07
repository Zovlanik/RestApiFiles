package com.zovlanik.restapifiles.view.servlets;

import com.zovlanik.restapifiles.controller.AccountController;
import com.zovlanik.restapifiles.controller.UserController;
import com.zovlanik.restapifiles.model.Account;
import com.zovlanik.restapifiles.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class CreateUser extends HttpServlet {
    UserController userController = new UserController();
    AccountController accountController = new AccountController();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String title = "Create User";
        String docType = "<!DOCTYPE html>";

        try {
            Account account = accountController.getById(Integer.parseInt(request.getParameter("account_id")));
            User newUser = new User(request.getParameter("username"),account);
            userController.create(newUser);


            writer.println(docType + "<html><head><title>" + title + "</title></head><body>");
            writer.println("<h1>Created User:</h1>");
            writer.println("User name = " + request.getParameter("username"));
            writer.println("<br/>");


        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

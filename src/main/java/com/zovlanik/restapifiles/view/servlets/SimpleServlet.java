package com.zovlanik.restapifiles.view.servlets;

import com.google.gson.Gson;
import com.zovlanik.restapifiles.model.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleServlet extends HttpServlet {
    private String message;

    public void init() throws ServletException {
        message = "I can write here something, but only in english :(";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");




        PrintWriter messageWriter = response.getWriter();
        messageWriter.println("<h1>" + message + "<h1>");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Account account = new Gson().fromJson(request.getReader(), Account.class);

        response.getWriter().println(account.getId());
        response.getWriter().println(account.getName());
        response.getWriter().println(account.getAccountStatus());

    }
    public void destroy() {


    }
}

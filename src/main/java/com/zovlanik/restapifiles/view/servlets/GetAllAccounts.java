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
import java.util.List;

public class GetAllAccounts extends HttpServlet {
   AccountController accountController = new AccountController();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String title = "Accounts Info";
        String docType = "<!DOCTYPE html>";

        try {
            List<Account> accounts = accountController.getAll();

            writer.println(docType + "<html><head><title>" + title + "</title></head><body>");
            writer.println("<button formaction=\"home.html\">Home</button>");
            writer.println("<h1>ACCOUNTS LIST</h1>");
            writer.println("<br/>");

            for(Account account : accounts) {
                writer.println("ID: " + account.getId());
                writer.println("Name: " + account.getName() + "<br/>");

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

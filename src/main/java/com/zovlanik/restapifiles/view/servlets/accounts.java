package com.zovlanik.restapifiles.view.servlets;

import com.google.gson.Gson;
import com.zovlanik.restapifiles.model.Account;
import com.zovlanik.restapifiles.repository.AccountRepository;
import com.zovlanik.restapifiles.repository.hibernate.HibernateAccountRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class accounts extends HttpServlet {
    private final AccountRepository accountRepository = new HibernateAccountRepositoryImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Gson gsonAccount = new Gson();
        if (id != null){
            response.getWriter().println(gsonAccount.toJson(accountRepository.getById(Integer.valueOf(id))));
        }else{
            response.getWriter().println(gsonAccount.toJson(accountRepository.getAll()));
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request,response);
    }
}

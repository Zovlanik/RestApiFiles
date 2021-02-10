package com.zovlanik.restapifiles.view.servlets;

import com.zovlanik.restapifiles.repository.UserRepository;
import com.zovlanik.restapifiles.repository.hibernate.HibernateUserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class users extends HttpServlet {
    private final UserRepository userRepository = new HibernateUserRepositoryImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

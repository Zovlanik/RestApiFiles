package com.zovlanik.restapifiles.view.servlets;

import com.google.gson.Gson;
import com.zovlanik.restapifiles.model.User;
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
        StringBuffer urlString = request.getRequestURL();

        Gson gsonUser = new Gson();
        int lastIndexOfSlash = urlString.lastIndexOf("/");
        String whatToReturn = urlString.substring(lastIndexOfSlash + 1);

        if (whatToReturn.equalsIgnoreCase("users")) {
            response.getWriter().println(gsonUser.toJson(userRepository.getAll()));

        } else {
            try {
                int userId = Integer.valueOf(whatToReturn);
                response.getWriter().println(gsonUser.toJson(userRepository.getById(userId)));
            } catch (Exception ex) {
                ex.printStackTrace();
                response.getWriter().println("Wrong type of parameter ID");
            }
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        try {
            User newUser = new Gson().fromJson(request.getReader(), User.class);
            userRepository.create(newUser);
            response.getWriter().println("User with name = " + newUser.getUsername() + " was successfully created.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        StringBuffer urlString = request.getRequestURL();


        try {
            int lastIndexOfSlash = urlString.lastIndexOf("/");
            int userId = Integer.parseInt(urlString.substring(lastIndexOfSlash + 1));
            User userToChange = new Gson().fromJson(request.getReader(), User.class);
            userToChange.setId(userId);
            userRepository.update(userToChange);
            response.getWriter().println("User was successfully changed.");
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer urlString = request.getRequestURL();

        int lastIndexOfSlash = urlString.lastIndexOf("/");


        try {
            int userId = Integer.parseInt(urlString.substring(lastIndexOfSlash + 1));
            userRepository.deleteById(userId);
            response.getWriter().println("User with id = " + userId + " was successfully deleted.");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.getWriter().println("Wrong type of parameter ID");
        }
    }

}

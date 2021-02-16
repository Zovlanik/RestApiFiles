package com.zovlanik.restapifiles.rest;

import com.google.gson.Gson;
import com.zovlanik.restapifiles.dto.EventDTO;
import com.zovlanik.restapifiles.dto.UserDTO;
import com.zovlanik.restapifiles.model.User;
import com.zovlanik.restapifiles.repository.UserRepository;
import com.zovlanik.restapifiles.repository.hibernate.HibernateUserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class UserRestControllerV1 extends HttpServlet {
    private final UserRepository userRepository = new HibernateUserRepositoryImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer urlString = request.getRequestURL();

        Gson gsonUser = new Gson();
        int lastIndexOfSlash = urlString.lastIndexOf("/");
        String whatToReturn = urlString.substring(lastIndexOfSlash + 1);

        if (whatToReturn.equalsIgnoreCase("users")) {
            response.getWriter().println(gsonUser.toJson(userRepository.getAll().stream()
                    .map(user -> {return new UserDTO(user.getId(),user.getUsername());})
                    .collect(Collectors.toList())));

        } else {
            try {
                int userId = Integer.valueOf(whatToReturn);
                User user = userRepository.getById(userId);
                UserDTO userDTO = new UserDTO(user.getId(),user.getUsername());
                response.getWriter().println(gsonUser.toJson(userDTO));
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

package com.zovlanik.restapifiles.view.servlets;

import com.google.gson.Gson;
import com.zovlanik.restapifiles.model.File;
import com.zovlanik.restapifiles.model.FileStatus;
import com.zovlanik.restapifiles.repository.FileRepository;
import com.zovlanik.restapifiles.repository.hibernate.HibernateFileRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class files extends HttpServlet {
    private final FileRepository fileRepository = new HibernateFileRepositoryImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer urlString = request.getRequestURL();

        Gson gsonFile = new Gson();
        int lastIndexOfSlash = urlString.lastIndexOf("/");
        String whatToReturn = urlString.substring(lastIndexOfSlash + 1);

        if (whatToReturn.equalsIgnoreCase("files")) {
            response.getWriter().println(gsonFile.toJson(fileRepository.getAll()));

        } else {
            try {
                int userId = Integer.valueOf(whatToReturn);
                response.getWriter().println(gsonFile.toJson(fileRepository.getById(userId)));
            } catch (Exception ex) {
                ex.printStackTrace();
                response.getWriter().println("Wrong type of parameter ID");
            }
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        try {
            File newFile = new Gson().fromJson(request.getReader(), File.class);
            if(newFile.getCreationDate() == null) {
                newFile.setCreationDate(new Date(System.currentTimeMillis()));
            }
            if(newFile.getStatus() == null){
                newFile.setStatus(FileStatus.ACTIVE);
            }

            fileRepository.create(newFile);
            response.getWriter().println("File with filename = " + newFile.getFilename() + " was successfully created.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        StringBuffer urlString = request.getRequestURL();


        try {
            int lastIndexOfSlash = urlString.lastIndexOf("/");
            int fileId = Integer.parseInt(urlString.substring(lastIndexOfSlash + 1));
            File fileToChange = new Gson().fromJson(request.getReader(), File.class);
            fileToChange.setId(fileId);
            fileRepository.update(fileToChange);
            response.getWriter().println("File was successfully changed.");
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer urlString = request.getRequestURL();

        int lastIndexOfSlash = urlString.lastIndexOf("/");


        try {
            int fileId = Integer.parseInt(urlString.substring(lastIndexOfSlash + 1));
            fileRepository.deleteById(fileId);
            response.getWriter().println("File with id = " + fileId + " was successfully deleted.");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.getWriter().println("Wrong type of parameter ID");
        }
    }
}

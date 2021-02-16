package com.zovlanik.restapifiles.rest;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.zovlanik.restapifiles.model.Event;
import com.zovlanik.restapifiles.model.File;
import com.zovlanik.restapifiles.model.FileStatus;
import com.zovlanik.restapifiles.repository.FileRepository;
import com.zovlanik.restapifiles.repository.hibernate.HibernateFileRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@MultipartConfig
public class FileRestControllerV1 extends HttpServlet {
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
        response.setContentType("multipart/mixed");

        File file = new File();
        String fileName = extractFileName(request.getPart("filename"));

        file.setFilename(fileName);
        file.setCreationDate(new Date());
        file.setStatus(FileStatus.ACTIVE);

        fileRepository.create(file);
        response.getWriter().println(new Gson().toJson(file, File.class));

    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
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

package com.zovlanik.restapifiles.rest;

import com.google.gson.Gson;
import com.zovlanik.restapifiles.dto.AccountDTO;
import com.zovlanik.restapifiles.dto.EventDTO;
import com.zovlanik.restapifiles.model.Event;
import com.zovlanik.restapifiles.repository.EventRepository;
import com.zovlanik.restapifiles.repository.hibernate.HibernateEventRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

public class EventRestControllerV1 extends HttpServlet {
    private final EventRepository eventRepository = new HibernateEventRepositoryImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer urlString = request.getRequestURL();

        Gson gsonEvent = new Gson();
        int lastIndexOfSlash = urlString.lastIndexOf("/");
        String whatToReturn = urlString.substring(lastIndexOfSlash + 1);

        if (whatToReturn.equalsIgnoreCase("events")) {
            response.getWriter().println(gsonEvent.toJson(eventRepository.getAll().stream()
                    .map(event -> {return new EventDTO(event.getUser_id(),event.getDate());})
                    .collect(Collectors.toList())));

        } else {
            try {
                int eventId = Integer.valueOf(whatToReturn);
                Event event = eventRepository.getById(eventId);
                EventDTO eventDTO = new EventDTO(event.getUser_id(),event.getDate());
                response.getWriter().println(gsonEvent.toJson(eventDTO));
            } catch (Exception ex) {
                ex.printStackTrace();
                response.getWriter().println("Wrong type of parameter ID");
            }
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        try {
            Event newEvent = new Gson().fromJson(request.getReader(), Event.class);
            if (newEvent.getDate() == null){
                newEvent.setDate(new Date(System.currentTimeMillis()));
            }
            eventRepository.create(newEvent);
            response.getWriter().println("Event with id = " + newEvent.getId() + " was successfully created.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        StringBuffer urlString = request.getRequestURL();


        try {
            int lastIndexOfSlash = urlString.lastIndexOf("/");
            int eventId = Integer.parseInt(urlString.substring(lastIndexOfSlash + 1));
            Event eventToChange = new Gson().fromJson(request.getReader(), Event.class);
            eventToChange.setId(eventId);
            eventRepository.update(eventToChange);
            response.getWriter().println("Event was successfully changed.");
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer urlString = request.getRequestURL();

        int lastIndexOfSlash = urlString.lastIndexOf("/");


        try {
            int eventId = Integer.parseInt(urlString.substring(lastIndexOfSlash + 1));
            eventRepository.deleteById(eventId);
            response.getWriter().println("Event with id = " + eventId + " was successfully deleted.");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.getWriter().println("Wrong type of parameter ID");
        }
    }
}

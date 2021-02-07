package com.zovlanik.restapifiles.controller;

import com.zovlanik.restapifiles.model.Event;
import com.zovlanik.restapifiles.repository.EventRepository;
import com.zovlanik.restapifiles.repository.hibernate.HibernateEventRepositoryImpl;


public class EventController {
    private final EventRepository eventRepository = new HibernateEventRepositoryImpl();

    public void create(Event event) {
        eventRepository.create(event);
    }

    public Event getById(int id){
        return eventRepository.getById(id);
    }

    public void update(Event newEvent){
        eventRepository.update(newEvent);
    }

    public void deleteById(int id){
        eventRepository.deleteById(id);
    }

}


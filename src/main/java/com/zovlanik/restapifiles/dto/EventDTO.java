package com.zovlanik.restapifiles.dto;

import com.zovlanik.restapifiles.model.Event;

import java.util.Date;

public class EventDTO {
    private Integer user_id;
    private Date date;

    public EventDTO() {
    }

    public EventDTO(Integer user_id, Date date) {
        this.user_id = user_id;
        this.date = date;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public static EventDTO fromEvent(Event event){
        return new EventDTO(event.getUser_id(),event.getDate());
    }
}

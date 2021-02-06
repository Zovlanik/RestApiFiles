package com.zovlanik.restapifiles.model;

import java.util.List;

public class User {
    int id;
    String username;
    Account account;
    List<File> files;
    List<Event> events;

    public User() {
    }

    public User(int id, String username, Account account, List<File> files, List<Event> events) {
        this.id = id;
        this.username = username;
        this.account = account;
        this.files = files;
        this.events = events;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}

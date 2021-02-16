package com.zovlanik.restapifiles.dto;

import com.zovlanik.restapifiles.model.User;

public class UserDTO {
    private Integer id;
    private String username;

    public UserDTO() {
    }

    public UserDTO(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static UserDTO fromUser(User user){
        return new UserDTO(user.getId(), user.getUsername());
    }
}

package com.zovlanik.restapifiles.controller;

import com.zovlanik.restapifiles.model.User;
import com.zovlanik.restapifiles.repository.UserRepository;
import com.zovlanik.restapifiles.repository.hibernate.HibernateUserRepositoryImpl;

import java.util.List;


public class UserController {
    private final UserRepository userRepository = new HibernateUserRepositoryImpl();

    public void create(User user) {
        userRepository.create(user);
    }

    public User getById(int id){
        return userRepository.getById(id);
    }

    public void update(User newUser){
        userRepository.update(newUser);
    }

    public void deleteById(int id){
        userRepository.deleteById(id);
    }

    public List<User> getAll(){
        return userRepository.getAll();
    }

}


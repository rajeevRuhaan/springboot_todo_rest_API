package com.todoapp.todoproject.service;

import com.todoapp.todoproject.model.User;

public interface UserService {
    public String addUser(User user);

    public String authentication(User user) ;
    
}

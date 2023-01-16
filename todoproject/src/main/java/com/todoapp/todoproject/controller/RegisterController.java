package com.todoapp.todoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoapp.todoproject.model.User;
import com.todoapp.todoproject.service.UserService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class RegisterController {
    @Autowired
    private UserService userService;

    // creating user
    @PostMapping("/signup")
    public String register(@RequestBody User user) {
        // send request body to userServiceinpl.java
      return  userService.addUser(user);
        
    }
}

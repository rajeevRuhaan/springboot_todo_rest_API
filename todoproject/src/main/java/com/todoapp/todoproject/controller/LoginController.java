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
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public String login(@RequestBody User user) {
      return  userService.authentication(user);
       
    }

    
}

package com.todoapp.todoproject.service;

import com.todoapp.todoproject.model.ForgotPassword;
import com.todoapp.todoproject.model.User;

public interface UserService {
    public String addUser(User user);

    public String authentication(User user);

    //public String changePassword(User user);

   // public String forgotPassword(ForgotPassword forgotPassword);
    
}

package com.todoapp.todoproject.service;

import com.todoapp.todoproject.model.Mail;

public interface EmailService {
    // method to send email
    String sendEmail(Mail mail);
}

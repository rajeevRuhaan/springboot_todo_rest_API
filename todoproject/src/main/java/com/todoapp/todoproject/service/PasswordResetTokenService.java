package com.todoapp.todoproject.service;

import com.todoapp.todoproject.model.PasswordResetToken;

public interface PasswordResetTokenService {
    public String save(PasswordResetToken passwordResetToken);
}

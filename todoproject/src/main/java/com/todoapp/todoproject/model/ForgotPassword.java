package com.todoapp.todoproject.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ForgotPassword {
    @Email(message="{NOT_VALID_EMAIL}")
    @NotEmpty(message="{EMAIL_REQUIRED}")
    private String email;
}

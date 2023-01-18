package com.todoapp.todoproject.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PasswordReset {
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;
    @NotEmpty
    private String token;
}

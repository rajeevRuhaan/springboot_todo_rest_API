package com.todoapp.todoproject.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PasswordReset {
    @NotEmpty
    private String resetPassword;
    @NotEmpty
    private String token;
}

package com.todoapp.todoproject.controller;

import java.net.http.HttpHeaders;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.todoapp.todoproject.model.ForgotPassword;
import com.todoapp.todoproject.model.Mail;
import com.todoapp.todoproject.model.PasswordReset;
import com.todoapp.todoproject.model.PasswordResetToken;
import com.todoapp.todoproject.model.User;
import com.todoapp.todoproject.repository.PasswordResetTokenRepository;
import com.todoapp.todoproject.repository.UserRepository;
import com.todoapp.todoproject.response.ResponseMessage;
import com.todoapp.todoproject.service.EmailService;
import com.todoapp.todoproject.service.UserService;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Value;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ChangePasswordController {
    
    @Autowired 
    UserService userService;
    @Autowired 
    EmailService emailService;
    @Autowired 
    UserRepository userRepository;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    
    @PostMapping("/forgot_password")
    public String provesPasswordForgot(@RequestBody ForgotPassword forgotPassword, HttpServletRequest request) {
        
        Optional<User> user = userRepository.findByEmail(forgotPassword.getEmail());

        if(!user.isPresent()) {
            return "invalid email";
        }
            User dbUser = user.get();
            PasswordResetToken token = new PasswordResetToken();
            token.setToken(UUID.randomUUID().toString());
            token.setExpirationDate(LocalDateTime.now().plusMinutes(30));
            token.setUser(dbUser);
            token = passwordResetTokenRepository.save(token);

            Mail mail = new Mail();
            mail.setFrom("rajeev.jkp@gmail.com");
            mail.setTo(dbUser.getEmail());
            mail.setSubject("Password reset request");
            String url = request.getScheme() + ":" + request.getServerName() + ":" + request.getServerPort() + "/reset-password?token=" + token.getToken();
            mail.setMessage("reset link: " + url);
            
            return emailService.sendEmail(mail);

    }

    @PostMapping("/reset_password/:token")
    public String processResetPassword(@RequestBody PasswordReset passwordReset) {

        return "" ;
    } 
}

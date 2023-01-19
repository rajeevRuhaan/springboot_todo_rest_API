package com.todoapp.todoproject.controller;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todoapp.todoproject.model.ForgotPassword;
import com.todoapp.todoproject.model.Mail;
import com.todoapp.todoproject.model.PasswordReset;
import com.todoapp.todoproject.model.PasswordResetToken;
import com.todoapp.todoproject.model.User;
import com.todoapp.todoproject.repository.PasswordResetTokenRepository;
import com.todoapp.todoproject.repository.UserRepository;
import com.todoapp.todoproject.service.EmailService;
import com.todoapp.todoproject.service.UserService;

import jakarta.servlet.http.HttpServletRequest;


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

    
    @PostMapping("/forgot-password")
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

    @PutMapping("/reset-password")
    public String processResetPassword(@RequestBody PasswordReset passwordReset, @RequestParam String token ) {

        // get data from database
        PasswordResetToken tokenDb = passwordResetTokenRepository.findByToken(token);
        System.out.println(tokenDb.getUser().getId());
// verify valid token or not
            
        if(tokenDb.getExpirationDate().isAfter(LocalDateTime.now())) {
            // find user by email
            User optUser = userRepository.findById(tokenDb.getUser().getId());
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String encryptPassword = bcrypt.encode(passwordReset.getResetPassword());
            optUser.setPassword(encryptPassword);
            User saveUser = userRepository.save(optUser);
            // check
           return saveUser.getEmail() + ": password_reset successfully" ;
        } else {
          return  "Your token is expired"; 
        }
        
        
    } 
}

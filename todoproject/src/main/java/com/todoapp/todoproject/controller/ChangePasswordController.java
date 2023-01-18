package com.todoapp.todoproject.controller;

import java.net.http.HttpHeaders;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.todoapp.todoproject.model.ForgotPassword;
import com.todoapp.todoproject.model.Mail;
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

    
    @PostMapping("/forgot_password")
    public String provesPasswordForgot(@RequestHeader HttpHeaders headers, @RequestBody ForgotPassword forgotPassword, HttpServletRequest request, BindingResult result, RedirectAttributes attributes, MessageSource messageSource) {
        
        Optional<User> user = userRepository.findByEmail(forgotPassword.getEmail());

        if(user.isPresent()) {
            User dbUser = user.get();
            PasswordResetToken token = new PasswordResetToken();
            token.setToken(UUID.randomUUID().toString());
            token.setExpirationDate(LocalDateTime.now().plusMinutes(30));
            token.setUser(dbUser);
            token = passwordResetTokenRepository.save(token);

            Mail mail = new Mail();
            mail.setFrom("no-reply@test.com");
            mail.setTo(dbUser.getEmail());
            mail.setSubject("Password reset request");
            
            Map<String, Object> mailModel = new HashMap<>();
            mailModel.put("token", token);
            mailModel.put("user", dbUser);
            mailModel.put("signature", "http://mohyehia.com");
            String url = request.getScheme() + request.getServerName() + ":" +request.getServerPort();
            mailModel.put("resetUrl", url + "/reset-password?token=" + token.getToken());
            mail.setModel(mailModel);
            
            emailService.send(mail);
            attributes.addFlashAttribute("success", messageSource.getMessage("PASSWORD_RESET_TOKEN_SENT", new Object[]{}, Locale.ENGLISH));

        }

        return "redirect:/forgot_password";
    }
}

package com.todoapp.todoproject.service;

import com.todoapp.todoproject.model.Mail;
import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


@Service
public class EmailServiceImpl implements EmailService {
    
    @Override
    public String sendEmail(Mail mail) {
        // get system properties
        Properties properties = new Properties();
        // SMTP host
		properties.put("mail.smtp.host", "smtp.gmail.com");
		// Is authentication enabled
		properties.put("mail.smtp.auth", "true");
		// Is TLS enabled
		properties.put("mail.smtp.starttls.enable", "true");
		// SSL Port
		properties.put("mail.smtp.socketFactory.port", "465");
		// SSL Socket Factory class
		properties.put("mail.smtp.ssl.enable", "true");
		// SMTP port, the same as SSL port :)
		properties.put("mail.smtp.port", "465");
		
		System.out.print("Creating the session...");
		
		// Create the session
		Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(mail.getFrom(), "ibiftefuggbkyyxv");
            }
        });
		session.setDebug(true);
		System.out.println("session done!");
        // variable for gmail
        MimeMessage mailMimeMessage = new MimeMessage(session);
        try {
            mailMimeMessage.setFrom(mail.getFrom());
            mailMimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getTo()));
            mailMimeMessage.setSubject(mail.getSubject());
            // Set message text
			mailMimeMessage.setText(mail.getMessage());
            // send mail
           Transport.send(mailMimeMessage);
            
            return "Mail sent successfully";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

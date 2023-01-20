package com.todoapp.todoproject.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.todoapp.todoproject.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
    private static final long EXPIRE_DURATION = 24 * 24 * 60 * 1000L; //24 hr

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;
    
    
    public String generateAccessToken (User user) {
        
        return Jwts.builder().setSubject(String.format("%s", user.getEmail()))
        .setId(String.format("%s", user.getId()))
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
        .compact();
    }
}

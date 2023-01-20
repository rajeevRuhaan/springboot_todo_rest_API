package com.todoapp.todoproject.controller;

import java.util.Base64;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TodoController {
    
    @PostMapping("/todos")
    public String create(@RequestHeader("Authorization") String bearerToken) {
        //split the token
        String[] chunks = bearerToken.split("\\.");
        System.out.println("chunk 2" + chunks[2]);
        Base64.Decoder decoder = Base64.getMimeDecoder();
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        
        System.out.println("header:" + header);
        System.out.println("payload:" + payload);
        

        return bearerToken;
        
    }

}

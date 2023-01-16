package com.todoapp.todoproject.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.todoapp.todoproject.exceptions.UserNotFoundException;
import com.todoapp.todoproject.model.User;
import com.todoapp.todoproject.repository.UserRepository;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String addUser(User user){
        // password encryption
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String encryptPassword = bcrypt.encode(user.getPassword());
        user.setPassword(encryptPassword);
        User saveUser = userRepository.save(user);
        return  saveUser.getEmail() + " added successfully";
    }


    @Override
    public String authentication(User user) throws UserNotFoundException  {
      
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        Optional<User> opUser = userRepository.findByEmail(user.getEmail());

         if(opUser.isPresent()){
            System.out.println("present");
             User dbUser = opUser.get();

        if (bcrypt.matches(user.getPassword(), dbUser.getPassword()))
                return "Authenticated User";
            else
                return "incorrect Password"; 
        }
            throw new UserNotFoundException("Unauthorised user"); 
       
       } 
}

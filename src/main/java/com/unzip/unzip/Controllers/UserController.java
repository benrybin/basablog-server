package com.unzip.unzip.Controllers;

import com.unzip.unzip.Models.User;
import com.unzip.unzip.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="users/add")
    public String addNewUser(@RequestBody User user){
        System.out.println(user);
        userRepository.save(user);
        return "Saved";
    }

    @PostMapping(path="/users/verify")
    public Boolean verifyUser(@RequestBody User user){
        System.out.println(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()));
        try {
            userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        }catch (NullPointerException e){
            return false; //"Invalid username or password";
        } return true; //"Successful Login! Welcome back " + user.getUsername();
    }

}


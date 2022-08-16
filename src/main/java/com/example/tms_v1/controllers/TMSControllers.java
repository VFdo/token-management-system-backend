package com.example.tms_v1.controllers;

import com.example.tms_v1.models.User;
import com.example.tms_v1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class TMSControllers {
    @Autowired
    UserRepository userReop;
    @GetMapping("/api/auth/signup")
    public String signup(){
        return "sign up page!";
    }

    @GetMapping("manage")
    public String manage(){
        return " manage page!";
    }

    @GetMapping("/home")
    public Optional<User> homepage(){
//        return "welcome to the homepage!";
        return userReop.findByUsername("mary");
    }

    @PostMapping("/testing")
    public String test(){
        System.out.println("works");
        return "this works fine!";
    }

    @GetMapping("/manager/home")
    public String managerhome(){
        return "welcome to the manager homepage!";
    }

    @GetMapping("/patient/home")
    public String patienthome(){
        return "welcome to the patient homepage!";
    }
}

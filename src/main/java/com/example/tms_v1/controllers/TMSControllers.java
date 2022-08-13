package com.example.tms_v1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TMSControllers {
    @GetMapping("/api/auth/signup")
    public String signup(){
        return "sign up page!";
    }

    @GetMapping("manage")
    public String manage(){
        return " manage page!";
    }

}

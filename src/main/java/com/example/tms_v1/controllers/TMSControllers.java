package com.example.tms_v1.controllers;

import com.example.tms_v1.models.Token;
import com.example.tms_v1.models.TokenState;
import com.example.tms_v1.models.User;
import com.example.tms_v1.payload.requests.SignupRequest;
import com.example.tms_v1.payload.requests.TokenRequest;
import com.example.tms_v1.repositories.TokenRepository;
import com.example.tms_v1.repositories.UserRepository;
import com.example.tms_v1.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class TMSControllers {
    @Autowired
    UserRepository userRepo;

    @Autowired
    TokenRepository tokenRepo;

    @Autowired
    TokenService tokenService;
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
        return userRepo.findByUsername("mary");
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

    // token methods - :TODO security
    @GetMapping("/tokens/all")  //done
    public List<Token> getAllTokens() {
        return tokenService.getAllTokens();
    }

    @PostMapping("tokens/new")
    public Token addNewToken(@Valid @RequestBody TokenRequest tokenRequest){
        Token t = new Token(tokenRequest.getPatientId(), tokenRequest.getDate(), TokenState.ACTIVE);
        return tokenService.createToken(t);
    }

//    @GetMapping("tokens/{patient}") :TODO - fix!!!
//    public List<Token> getMyTokens(@PathVariable ("patient") String patient){
//        return tokenService.getAllTokensPatient(patient);
//    }

    @PostMapping("tokens/deactivate")
    public List<Token> changeState(String date){
        List<Token> t = tokenService.updateToken(date);
        return t;
    }

//    User Methods
    @GetMapping("users/all")
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

}



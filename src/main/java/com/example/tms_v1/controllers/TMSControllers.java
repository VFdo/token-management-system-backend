package com.example.tms_v1.controllers;

import com.example.tms_v1.models.Token;
import com.example.tms_v1.models.TokenState;
import com.example.tms_v1.models.User;
import com.example.tms_v1.payload.requests.TokenRequest;
import com.example.tms_v1.repositories.TokenRepository;
import com.example.tms_v1.repositories.UserRepository;
import com.example.tms_v1.services.TokenService;
import com.example.tms_v1.services.UserDetailsServiceImpl;
import com.example.tms_v1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @Autowired
    UserService userService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    //    **************** TOKEN FEATURES ****************
    @GetMapping("/tokens/all")  //done
    public List<Token> getAllTokens() {
        return tokenService.getAllTokens();
    }

    @PostMapping("tokens/new")
    public Token addNewToken(@Valid @RequestBody TokenRequest tokenRequest){
        int tokenNumber = tokenService.getTokenCount();
        Token t = new Token(tokenRequest.getPatientId(), tokenRequest.getPatient(),tokenRequest.getDate(), tokenNumber, TokenState.ACTIVE);
        return tokenService.createToken(t);
    }

    @GetMapping("tokens/all/{patientId}")
    public List<Token> getUserTokens(@PathVariable ("patientId") String patientId){
        return tokenService.getTokensPatient(patientId);
    }

    @PostMapping("tokens/deactivate")
    public List<Token> changeState(String date){
        List<Token> t = tokenService.deactivateToken(date);
        return t;
    }

    @DeleteMapping("/tokens/delete")
    public String deleteToken(@RequestBody Token t){
        return tokenService.deleteToken(t);
    }

    //    **************** USER FEATURES ****************
    @GetMapping("users/all")
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @PostMapping("user/details")
    public Optional<User> getUser(@RequestBody User user){
        return userService.findUser(user);
    }

    @PostMapping("user/update")
    public Optional<User> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("user/delete/{userId}")
    public String delUser(@PathVariable ("userId") String userId){
        return userService.delUser(userId);
    }
    @PostMapping("user/delete")
    public String deleteUser(@RequestBody User user){
        return userService.deleteUser(user);
    }
}



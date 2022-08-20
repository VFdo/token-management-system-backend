package com.example.tms_v1.services;

import com.example.tms_v1.models.Token;
import com.example.tms_v1.models.TokenState;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {
    TokenService tokenService;

    @Test
    void getAllTokensPatient() {
//        Token t = new Token("6300465bbf0079442f5f2571", "david", "2022-08-20", 16, TokenState.ACTIVE);
//        int expected = 2;
//        List<Token> list = tokenService.getAllTokensPatient(t);
//        System.out.println(list.get(0));
//        assertEquals(2, tokenService.getAllTokensPatient(t).size());
    }



    @Test
    void getAllTokens() {
//        int expected = 17;
//        List<Token> list = tokenService.getAllTokens();
//        System.out.println(list.get(0));
//        assertEquals(expected, list.size());
    }

    @Test
    void createToken() {

    }

    @Test
    void deactivateToken() {
    }

    @Test
    void getTokenCount() {
    }

    @Test
    void deleteToken() {
    }
}
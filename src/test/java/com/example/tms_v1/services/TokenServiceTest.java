package com.example.tms_v1.services;

import com.example.tms_v1.models.Token;
import com.example.tms_v1.models.TokenState;
import com.example.tms_v1.models.User;
import com.example.tms_v1.repositories.TokenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Provider;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TokenServiceTest {
    @Mock
    TokenRepository mockRepo;
    @Autowired @InjectMocks
    TokenService tokenService;
    @Captor
    private ArgumentCaptor<Token> argumentCaptor;
    List<Token> mocklist = new ArrayList<>();
    Token t1 = Mockito.mock(Token.class);

    @Test
    void getAllTokens() {
        mocklist.add(t1);
        when(tokenService.getAllTokens()).thenReturn(mocklist);
        List<Token> actual = tokenService.getAllTokens();
        assertEquals(mocklist.get(0), actual.get(0));
    }

    @Test
    void getTokensPatient() {
        mocklist.add(t1);
        when(tokenService.getTokensPatient(t1.getPatientid())).thenReturn(mocklist);
        List<Token> actual = tokenService.getTokensPatient(t1.getPatientid());
        assertEquals(mocklist, actual);
    }
}
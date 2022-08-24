package com.example.tms_v1.services;

import com.example.tms_v1.models.Token;
import com.example.tms_v1.models.User;
import com.example.tms_v1.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceTest {
    @Mock
    UserRepository userRepo;

    @Autowired @InjectMocks
    UserService userService;
    @Captor
    private ArgumentCaptor<User> argumentCaptor;

    List<User> mocklist = new ArrayList<>();
    User u1 = Mockito.mock(User.class);


//    @Test
//    void findUser() {
//        mocklist.add(u1);
//        when(userService.findUser(u1)).thenReturn(Optional.ofNullable(mocklist.get(0)));
//        Optional<User> actual = userService.findUser(u1);
//        assertEquals(mocklist.get(0), actual);
//    }
}
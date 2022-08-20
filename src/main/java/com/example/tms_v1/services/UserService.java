package com.example.tms_v1.services;

import com.example.tms_v1.models.User;
import com.example.tms_v1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    public Optional<User> updateUser(User newUser) {
        Optional<User> user = userRepository.findById(newUser.getId());
        user.orElseThrow(RuntimeException::new).setFullName(newUser.getFullName());
        if(newUser.getFullName()!=null) {
            String[] names = newUser.getFullName().trim().split(" ");
            user.orElseThrow(RuntimeException::new).setFirstName(names[0]);
            user.orElseThrow(RuntimeException::new).setLastName(names[names.length - 1]);
        }
        user.orElseThrow(RuntimeException::new).setEmail(newUser.getEmail());
        if(newUser.getPassword()!=""){
            user.orElseThrow(RuntimeException::new).setPassword(encoder.encode(newUser.getPassword()));
        }
        user.orElseThrow(RuntimeException::new).setPhoneNo(newUser.getPhoneNo());
        user.orElseThrow(RuntimeException::new).setUsername(newUser.getUsername());
        user.orElseThrow(RuntimeException::new).setDateOfBirth(newUser.getDateOfBirth());
        userRepository.save(user.orElseThrow(RuntimeException::new));
        return user;
    }

    public Optional<User> findUser(User u) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(u.getId());
        return user;
    }

    public String deleteUser(User user) {
        userRepository.deleteById(user.getId());
        return "deleted user";
    }
}

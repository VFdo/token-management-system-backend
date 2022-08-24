package com.example.tms_v1.services;

import com.example.tms_v1.models.User;
import com.example.tms_v1.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    public Optional<User> updateUser(User newUser) {
        try{
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
            logger.trace("user updated");
            return user;
        }
        catch (Exception ex){
            logger.error(String.valueOf(ex));
            return null;
        }
    }

    public Optional<User> findUser(User u) throws UsernameNotFoundException {
        try{
            Optional<User> user = userRepository.findById(u.getId());
            return user;
        }
        catch (Exception ex){
            logger.error(String.valueOf(ex));
            return null;
        }
    }

    public String deleteUser(User user) {
        try{
            userRepository.deleteById(user.getId());
            logger.trace("user deleted");
            return "deleted user";
        }
        catch (Exception ex){
            logger.error(String.valueOf(ex));
            return null;
        }
    }

    public String delUser(String userId) {
        userRepository.deleteById(userId);
        logger.trace("user deleted");
        return "deleted user";
    }
}

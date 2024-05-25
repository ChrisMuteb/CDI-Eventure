package com.lasuperbe.server.service.Impl;

import com.lasuperbe.server.entity.User;
import com.lasuperbe.server.exception.ServerCustomException;
import com.lasuperbe.server.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        Optional<User> existingUser = userRepository.findUserByEmail(user.getEmail());
        if(existingUser.isPresent())
            throw new RuntimeException("User already exist");
        else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            return userRepository.save(user);
        }
    }

    public User findUserById(Integer userID){
        Optional<User> user = userRepository.findById(userID);
        if(user.isEmpty())
            throw new ServerCustomException("User with given id not found", "USER_NOT_FOUND");
        User result = user.get();
        return result;
    }

    public User updateUserRole(Integer userID, String role){
        User existingUser = findUserById(userID);
        existingUser.setRole(role);
        return userRepository.save(existingUser);
    }

    public void deleteUser(Integer userID){
        User existingUser = findUserById(userID);
        userRepository.delete(existingUser);
    }
}

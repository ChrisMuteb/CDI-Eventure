package com.lasuperbe.server.service.Impl;

import com.lasuperbe.server.entity.User;
import com.lasuperbe.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUser(){
        return userRepository.findAll();
    }
    public User addUser(User user){
        return userRepository.save(user);
    }

    public User findUserById(Integer userID){
        Optional<User> user = userRepository.findById(userID);
        if(user.isEmpty())
            throw new RuntimeException();
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

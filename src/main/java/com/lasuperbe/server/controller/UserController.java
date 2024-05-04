package com.lasuperbe.server.controller;

import com.lasuperbe.server.dto.EventDTO;
import com.lasuperbe.server.entity.Event;
import com.lasuperbe.server.entity.User;
import com.lasuperbe.server.repository.EventRepository;
import com.lasuperbe.server.repository.UserRepository;
import com.lasuperbe.server.service.Impl.EventService;

import com.lasuperbe.server.service.Impl.UserService;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    {
        BasicConfigurator.configure();
    }

    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.findAllUser();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){
        User user = userService.findUserById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
         User result = userService.addUser(user);
         if(result == null) return new ResponseEntity<User>(HttpStatus.CONFLICT);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<User>(headers, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestParam(name = "role") String role){
        User updatedUser = userService.updateUserRole(id, role);

        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return new ResponseEntity<String>("User deleted" + id, HttpStatus.NO_CONTENT);
    }

}

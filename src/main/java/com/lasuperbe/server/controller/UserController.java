package com.lasuperbe.server.controller;

import com.lasuperbe.server.dto.EventDTO;
import com.lasuperbe.server.entity.Event;
import com.lasuperbe.server.entity.User;
import com.lasuperbe.server.repository.EventRepository;
import com.lasuperbe.server.repository.UserRepository;
import com.lasuperbe.server.service.EventService;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    {
        BasicConfigurator.configure();
    }
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventService eventService;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new RuntimeException();
        return user.get();
    }
    @PostMapping("/users")
    public User saveUser(@RequestBody User user){
        return userRepository.save(user);
    }
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user){
        User existingUser = userRepository.findById(id).orElse(null);

        existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new RuntimeException();
        userRepository.deleteById(id);
        return "User deleted" ;
    }

    @PostMapping("/users/{id}/events")
    public Event userAddEvent(@PathVariable int id, @RequestBody EventDTO eventDTO){
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new RuntimeException();

        Event event = eventService.convertDTOToEntity(eventDTO);
        event.setUser(user.orElse(null));

        Event savedEvent = eventRepository.save(event);
        logger.debug("savedEvent log: " + savedEvent);

        return savedEvent;
    }
    @GetMapping("/users/{id}/events")
    public List<Event> userEvents(@PathVariable int id){
        logger.debug("find all the events related to the userId");
        List<Event> events = eventRepository.findAllByUser_Id(id);
        logger.debug("events belonging to the user is complete");

        return events;
    }
    @GetMapping("/users/{id}/events/{eventID}")
    public Event userEvent(@PathVariable int id, @PathVariable int eventID){
        logger.debug("find an event related to the userId" + id + " eventID: " + eventID);
//
        Event event = eventRepository.findEventByUser_Id(id, eventID);

        return event;
    }
    @DeleteMapping("/users/{id}/events/{eventID}")
    public String deleteUserEvent(@PathVariable int id, @PathVariable int eventID){
        logger.debug("find all the events related to the userId");
        List<Event> events = eventRepository.findAllByUser_Id(id);
        Event event = events.stream().filter(e -> e.getUser().getUserID()==eventID).findFirst().get();
        eventRepository.delete(event);

        logger.debug("events deleted: complete");

        return "event has been deleted";
    }

}

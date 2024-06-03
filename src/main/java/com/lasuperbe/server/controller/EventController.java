package com.lasuperbe.server.controller;

import com.lasuperbe.server.dto.EventDTO;
import com.lasuperbe.server.entity.Event;
import com.lasuperbe.server.entity.User;
import com.lasuperbe.server.repository.EventRepository;
import com.lasuperbe.server.service.Impl.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/v1/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getEvents(){
        List<Event> events = eventService.findAllEvent();
        if (events != null ) {
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                    .body(events);
        }else {
            return null;
        }
//        return new ResponseEntity<List<Event>>(events, HttpStatus.OK).;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable int id){
        Event user = eventService.findEventById(id);
        return new ResponseEntity<Event>(user, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Event> saveEvent(@RequestBody Event event){
        Event result = eventService.addEvent(event);
        if(result == null) return new ResponseEntity<Event>(HttpStatus.CONFLICT);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Event>(headers, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable int id, @RequestParam(name = "description") String description){
        Event updatedEvent = eventService.updateEventDescription(id, description);

        return new ResponseEntity<Event>(updatedEvent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable int id){
        eventService.deleteEvent(id);
        return new ResponseEntity<String>("Event deleted" + id, HttpStatus.NO_CONTENT);
    }

}

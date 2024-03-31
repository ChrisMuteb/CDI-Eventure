package com.lasuperbe.server.controller;

import com.lasuperbe.server.dto.EventDTO;
import com.lasuperbe.server.entity.Event;
import com.lasuperbe.server.repository.EventRepository;
import com.lasuperbe.server.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class EventController {
    private EventRepository eventRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/events")
    public List<Event> getEvents(){
        return eventRepository.findAll();
    }
    @PostMapping("/events")
    public Event saveEvent(@RequestBody EventDTO eventDTO){
        Event event = eventService.convertDTOToEntity(eventDTO);
        eventRepository.save(event);
        return event;
    }
}

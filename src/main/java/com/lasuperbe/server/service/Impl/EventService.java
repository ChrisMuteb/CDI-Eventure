package com.lasuperbe.server.service.Impl;

import com.lasuperbe.server.dto.EventDTO;
import com.lasuperbe.server.entity.Event;
import com.lasuperbe.server.entity.User;
import com.lasuperbe.server.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    public List<Event> findAllEvent(){
        return eventRepository.findAll();
    }
    public Event addEvent(@RequestBody Event event){
        eventRepository.save(event);
        return event;
    }

    public Event findEventById(Integer eventID){
        Optional<Event> event = eventRepository.findById(eventID);
        if(event.isEmpty())
            throw new RuntimeException();
        Event result = event.get();
        return result;
    }

    public Event updateEventDescription(Integer eventID, String description){
        Event existingEvent = findEventById(eventID);
        existingEvent.setDescription(description);
        return eventRepository.save(existingEvent);
    }

    public void deleteEvent(Integer eventID){
        Event existingEvent = findEventById(eventID);
        eventRepository.delete(existingEvent);
    }
}

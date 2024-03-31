package com.lasuperbe.server.service;

import com.lasuperbe.server.dto.EventDTO;
import com.lasuperbe.server.entity.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventService {
    public Event convertDTOToEntity(EventDTO eventDTO) {
        Event event = new Event();
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setDateTime(LocalDateTime.parse(eventDTO.getDatetime())); // Convert string to LocalDateTime
        event.setLocation(eventDTO.getLocation());

        return event;
    }
}

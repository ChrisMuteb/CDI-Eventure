package com.lasuperbe.server.controller;

import com.lasuperbe.server.entity.Participant;
import com.lasuperbe.server.service.Impl.EventService;
import com.lasuperbe.server.service.Impl.ParticipantService;
import com.lasuperbe.server.service.Impl.ParticipantService;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/participants")
public class ParticipantController {
    private static final Logger logger = Logger.getLogger(ParticipantController.class);
    {
        BasicConfigurator.configure();
    }

    @Autowired
    private ParticipantService participantService;

    @GetMapping
    public ResponseEntity<List<Participant>> getParticipants(){
        List<Participant> participants = participantService.findAllParticipant();
        return new ResponseEntity<List<Participant>>(participants, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Participant> getParticipant(@PathVariable int id){
        Participant participant = participantService.findParticipantById(id);
        return new ResponseEntity<Participant>(participant, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Participant> saveParticipant(@RequestBody Participant participant){
         Participant result = participantService.addParticipant(participant);
         if(result == null) return new ResponseEntity<Participant>(HttpStatus.CONFLICT);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Participant>(headers, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable int id, @RequestParam(name = "role") String role){
        Participant updatedParticipant = participantService.updateParticipantRole(id, role);

        return new ResponseEntity<Participant>(updatedParticipant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParticipant(@PathVariable int id){
        participantService.deleteParticipant(id);
        return new ResponseEntity<String>("Participant deleted" + id, HttpStatus.NO_CONTENT);
    }
}

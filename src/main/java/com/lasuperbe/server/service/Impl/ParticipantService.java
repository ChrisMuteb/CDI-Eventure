package com.lasuperbe.server.service.Impl;

import com.lasuperbe.server.entity.Participant;
import com.lasuperbe.server.exception.ServerCustomException;
import com.lasuperbe.server.repository.ParticipantRepository;
import com.lasuperbe.server.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    public List<Participant> findAllParticipant(){
        return participantRepository.findAll();
    }
    public Participant addParticipant(Participant participant){
        Optional<Participant> existingParticipant = participantRepository.findParticipantByUserID(participant.getUser().getUserID());
        if(existingParticipant.isPresent())
            throw new RuntimeException();
        return participantRepository.save(participant);
    }

    public Participant findParticipantById(Integer participantID){
        Optional<Participant> participant = participantRepository.findById(participantID);
        if(participant.isEmpty())
            throw new ServerCustomException("Participant with given id not found", "PARTICIPANT_NOT_FOUND");
        Participant result = participant.get();
        return result;
    }

    public Participant updateParticipantRole(Integer participantID, String role){
        Participant existingParticipant = findParticipantById(participantID);
        existingParticipant.setRole(role);
        return participantRepository.save(existingParticipant);
    }

    public void deleteParticipant(Integer participantID){
        Participant existingParticipant = findParticipantById(participantID);
        participantRepository.delete(existingParticipant);
    }
}

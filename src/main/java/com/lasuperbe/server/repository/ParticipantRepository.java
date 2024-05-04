package com.lasuperbe.server.repository;

import com.lasuperbe.server.entity.Event;
import com.lasuperbe.server.entity.Participant;
import com.lasuperbe.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
    @Query("SELECT e FROM Participant e WHERE e.user.userID = :id")
    Optional<Participant> findParticipantByUserID(int id);
}

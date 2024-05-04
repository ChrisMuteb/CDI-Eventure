package com.lasuperbe.server.repository;

import com.lasuperbe.server.entity.Participant;
import com.lasuperbe.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
}

package com.lasuperbe.server.repository;

import com.lasuperbe.server.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("SELECT e FROM Event e WHERE e.user.userID = :userId")
    List<Event> findAllByUser_Id(Integer userId);

    @Query("SELECT e FROM Event e WHERE e.user.userID = :userId and e.eventID= :eventId")
    Event findEventByUser_Id(Integer userId, Integer eventId);
}

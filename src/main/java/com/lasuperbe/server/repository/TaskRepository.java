package com.lasuperbe.server.repository;

import com.lasuperbe.server.entity.Event;
import com.lasuperbe.server.entity.Participant;
import com.lasuperbe.server.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT e FROM Task e WHERE e.title = :title")
    Optional<Task> findTaskByTitle(String title);
}

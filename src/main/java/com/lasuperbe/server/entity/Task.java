package com.lasuperbe.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer taskID;
    private String title;
    private String description;
    private String status;
    @Future(message = "event date should be in the future")
    private LocalDateTime deadline;
    @ManyToOne
    @JoinColumn(name = "eventID")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "assignedID")
    private User user;

    @Override
    public String toString() {
        return "Task{" +
                "taskID=" + taskID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", deadline=" + deadline +
                ", event=" + event +
                ", user=" + user +
                '}';
    }
}

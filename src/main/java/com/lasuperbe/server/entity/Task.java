package com.lasuperbe.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
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

    public Task() {
    }

    public Task(Integer taskID, String title, String description, String status, LocalDateTime deadline, Event event, User user) {
        this.taskID = taskID;
        this.title = title;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
        this.event = event;
        this.user = user;
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

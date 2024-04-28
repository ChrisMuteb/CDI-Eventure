package com.lasuperbe.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue
    private Integer taskID;
    private String title;
    private String description;
    private String status;
    @Future(message = "event date should be in the future")
    private LocalDateTime datetime;
    @ManyToOne
    @JsonIgnore
    private Event EventID;
    @ManyToOne
    private User assignedID;

    public Task() {
    }

    public Task(Integer taskID, String title, String description, String status, LocalDateTime datetime, Event eventID, User assignedID) {
        this.taskID = taskID;
        this.title = title;
        this.description = description;
        this.status = status;
        this.datetime = datetime;
        EventID = eventID;
        this.assignedID = assignedID;
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

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public Event getEventID() {
        return EventID;
    }

    public void setEventID(Event eventID) {
        EventID = eventID;
    }

    public User getAssignedID() {
        return assignedID;
    }

    public void setAssignedID(User assignedID) {
        this.assignedID = assignedID;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskID=" + taskID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", datetime=" + datetime +
                ", EventID=" + EventID +
                ", assignedID=" + assignedID +
                '}';
    }
}

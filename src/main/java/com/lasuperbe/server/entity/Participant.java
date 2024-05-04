package com.lasuperbe.server.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "participant")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer participantID;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "eventID")
    private Event event;
    private String role;

    public Participant() {
    }

    public Participant(Integer participantID, User user, Event event, String role) {
        this.participantID = participantID;
        this.user = user;
        this.event = event;
        this.role = role;
    }

    public Integer getParticipantID() {
        return participantID;
    }

    public void setParticipantID(Integer participantID) {
        this.participantID = participantID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "participantID=" + participantID +
                ", user=" + user +
                ", event=" + event +
                ", role='" + role + '\'' +
                '}';
    }
}

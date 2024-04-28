package com.lasuperbe.server.entity;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class Participant {
    private Integer participantID;
    @OneToOne
    private User userID;
    @ManyToOne
    private Event eventID;
    private String role;
}

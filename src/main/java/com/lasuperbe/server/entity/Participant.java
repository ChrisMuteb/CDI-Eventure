package com.lasuperbe.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "participant")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

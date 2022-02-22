package com.sport_complex.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Ticket{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String userName;
    private Integer userAge;
    private String ticketType;

    public Ticket() {
    }

    public Ticket(String userName, Integer userAge, String ticketType) {
        this.userName = userName;
        this.userAge = userAge;
        this.ticketType = ticketType;
    }
}

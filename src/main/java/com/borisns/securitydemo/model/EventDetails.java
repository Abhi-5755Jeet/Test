package com.borisns.securitydemo.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "events")
public class EventDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long event_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    @Column(name = "location", nullable = false)
    private String location;



    // Getters and Setters


    public EventDetails(Long event_id, String name, Timestamp date, String location) {
        this.event_id = event_id;
        this.name = name;
        this.date = date;
        this.location = location;

    }



    public EventDetails() {

    }

    public Long getId() {
        return event_id;
    }

    public void setId(Long id) {
        this.event_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}

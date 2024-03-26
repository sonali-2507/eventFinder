package com.location.location.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@Entity(name = "locationInfo")
public class LocationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String event_name;
    private String city_name;
    private Date date;
    private Time time;
    @Column(columnDefinition = "DOUBLE")
    double latitude;
    @Column(columnDefinition = "DOUBLE")
    private double longitude;


}

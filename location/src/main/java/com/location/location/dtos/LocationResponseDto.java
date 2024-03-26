package com.location.location.dtos;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
public class LocationResponseDto {
    private String event_name;
    private String city_name;
    private Date date;
    private String weather;
    private double distance_km;

}

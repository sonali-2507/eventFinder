package com.location.location.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class LocationRequestDto {
    private double latitude;
    private double longitude;
    private Date date;
}

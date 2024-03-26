package com.location.location.services;
import com.location.location.dtos.LocationResponseDto;
import com.location.location.models.LocationInfo;
import org.springframework.http.ResponseEntity;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface ILocationService {
    ResponseEntity<List<LocationResponseDto>> find(double latitude, double longitude, Date date);
    LocationInfo save(String event_name, String city_name, Date date, Time time, double latitude, double longitude);
}

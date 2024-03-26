package com.location.location.controllers;

import com.location.location.dtos.LocationRequestDto;
import com.location.location.dtos.LocationResponseDto;
import com.location.location.models.LocationInfo;
import com.location.location.services.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {
    private ILocationService locationService;
    @Autowired
    public LocationController(ILocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<LocationResponseDto>>find(@RequestBody LocationRequestDto request){

        return locationService.find(request.getLatitude(), request.getLongitude(), request.getDate());
    }
    @PostMapping("/save")
    public LocationInfo save(@RequestBody LocationInfo locationInfo){
        return locationService.save(locationInfo.getEvent_name(), locationInfo.getCity_name(), locationInfo.getDate(), locationInfo.getTime(), locationInfo.getLatitude(), locationInfo.getLongitude());
    }


}

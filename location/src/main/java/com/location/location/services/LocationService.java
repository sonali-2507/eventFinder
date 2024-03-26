package com.location.location.services;

import com.location.location.dtos.LocationResponseDto;
import com.location.location.models.LocationInfo;
import com.location.location.repositories.LocationRepository;
import com.location.location.thirdPartyClients.DistanceClient;
import com.location.location.thirdPartyClients.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
@Service
public class LocationService implements ILocationService{
    private LocationRepository locationRepository;
    @Autowired
    private WeatherClient weatherClient;
    private DistanceClient distanceClient;
    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    @Override
    public ResponseEntity<List<LocationResponseDto>> find(double latitude, double longitude, Date date) {
        List<LocationResponseDto>locationResponseDtos = new ArrayList<>();

       List<LocationInfo> locationInfoList = locationRepository.findByLatitudeAndLongitudeAndDate(latitude, longitude,date);

       for(LocationInfo locationInfo: locationInfoList){
              LocationResponseDto locationResponseDto = new LocationResponseDto();
           weatherClient = new WeatherClient(locationInfo.getCity_name(),date);
           distanceClient = new DistanceClient(latitude,longitude);
           String weather = weatherClient.getWeather(weatherClient.getBaseUrl());
           Double distance = distanceClient.getDistance(distanceClient.getBaseUrl());
           locationResponseDto.setWeather(weather);
           locationResponseDto.setDistance_km(distance);
           locationResponseDto.setCity_name(locationInfo.getCity_name());
           locationResponseDto.setEvent_name(locationInfo.getEvent_name());
           locationResponseDto.setDate(locationInfo.getDate());
              locationResponseDtos.add(locationResponseDto);
       }
       return ResponseEntity.ok(locationResponseDtos);
    }
   @Override
    public LocationInfo save(String event_name, String city_name, Date date, Time time, double latitude, double longitude) {
        LocationInfo locationInfo = new LocationInfo();
        locationInfo.setEvent_name(event_name);
        locationInfo.setCity_name(city_name);
        locationInfo.setDate(date);
        locationInfo.setTime(time);
        locationInfo.setLatitude(latitude);
        locationInfo.setLongitude(longitude);
        return locationRepository.save(locationInfo);
    }
}

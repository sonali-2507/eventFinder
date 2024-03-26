package com.location.location.services;

import com.location.location.dtos.LocationResponseDto;
import com.location.location.dtos.SortValue;
import com.location.location.models.LocationInfo;
import com.location.location.repositories.LocationRepository;
import com.location.location.thirdPartyClients.DistanceClient;
import com.location.location.thirdPartyClients.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@Service
public class SearchService implements ISearchService{
    private LocationRepository locationRepository;
    private WeatherClient weatherClient;
    private DistanceClient distanceClient;
    @Autowired
    public SearchService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    @Override
    public Page<LocationResponseDto> search( Date query,int pageNumber, int pageSize, List<SortValue> sortValues) {
        Sort sort = Sort.unsorted();
        for (SortValue sortValue : sortValues) {
            sort = Sort.by(Sort.Direction.fromString(sortValue.getSortType()), sortValue.getSortField());
        }
        //create pageable object
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<LocationInfo> locationPage = locationRepository.findAll(query,pageable);
        List<LocationInfo> locationInfoList = locationPage.get().toList();
        List<LocationResponseDto> eventList = new ArrayList<>();
        for (LocationInfo locationInfo : locationInfoList) {
           eventList.add(mapper(locationInfo));
        }
        Page<LocationResponseDto> locationResponsePage = new PageImpl<LocationResponseDto>(eventList, locationPage.getPageable(), locationPage.getTotalElements());
        return locationResponsePage;
    }

    public LocationResponseDto mapper(LocationInfo locationInfo){
        LocationResponseDto locationResponseDto = new LocationResponseDto();
        locationResponseDto.setCity_name(locationInfo.getCity_name());
        locationResponseDto.setEvent_name(locationInfo.getEvent_name());
        locationResponseDto.setDate(locationInfo.getDate());
        weatherClient = new WeatherClient(locationInfo.getCity_name(),locationInfo.getDate());
        distanceClient = new DistanceClient( locationInfo.getLatitude(),locationInfo.getLongitude());
        String weather = weatherClient.getWeather(weatherClient.getBaseUrl());
        Double distance = distanceClient.getDistance(distanceClient.getBaseUrl());
        locationResponseDto.setWeather(weather);
        locationResponseDto.setDistance_km(distance);
        return locationResponseDto;
    }
}

package com.location.location.controllers;

import com.location.location.dtos.LocationResponseDto;
import com.location.location.dtos.SearchRequestDto;
import com.location.location.services.ISearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class SearchController {
    private ISearchService searchService;
    public SearchController(ISearchService searchService) {
        this.searchService = searchService;
    }
    @PostMapping("/find")
    public Page<LocationResponseDto> search (@RequestBody SearchRequestDto request){
        return searchService.search( request.getQuery(), request.getPageNumber(),
                request.getPageSize(), request.getSortValues());
    }
}

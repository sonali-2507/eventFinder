package com.location.location.services;

import com.location.location.dtos.LocationResponseDto;
import com.location.location.dtos.SortValue;
import org.springframework.data.domain.Page;
import java.sql.Date;
import java.util.List;

public interface ISearchService {
     Page<LocationResponseDto> search (Date query, int pageNumber, int pageSize, List<SortValue> sortValues);

}

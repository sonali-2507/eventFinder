package com.location.location.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
@Getter
@Setter
public class SearchRequestDto {
    private Date query;
    private int pageNumber;
    private int pageSize;
    private List<SortValue> sortValues;
}

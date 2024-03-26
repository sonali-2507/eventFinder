package com.location.location.repositories;
import com.location.location.models.LocationInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationInfo, Long>{
 @Query(value = "select * from location_info where latitude = :latitude and longitude = :longitude and date between :date and date_add(date,interval 14 DAY)", nativeQuery = true)
 List< LocationInfo> findByLatitudeAndLongitudeAndDate(double latitude, double longitude, java.sql.Date date);

 @Query(value = "SELECT * FROM location_info WHERE date >= :date AND date < DATE_ADD(:date, INTERVAL 14 DAY)", nativeQuery = true)
 Page<LocationInfo> findAll(Date date,Pageable pageable);

}


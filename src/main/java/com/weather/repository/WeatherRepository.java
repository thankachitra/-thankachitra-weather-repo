package com.weather.repository;

import java.util.Date;
import java.util.List;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.weather.entity.Weather;



public interface WeatherRepository extends PagingAndSortingRepository<Weather, Long>{
	// in order to sort the records by id in ascending order ,use PagingAndSortingRepository instead JpaRepository
	public List<Weather> findByDate(Date date);
	    
}

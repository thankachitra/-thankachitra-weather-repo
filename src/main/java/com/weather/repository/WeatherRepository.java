package com.weather.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.weather.entity.Weather;


	public interface WeatherRepository extends JpaRepository<Weather, Long>{
		public List<Weather> findByDate(Date date);
	}

package com.weather.repository;

	import java.util.Date;
import java.util.Optional;

	import org.springframework.data.jpa.repository.JpaRepository;

import com.weather.entity.Weather;


	public interface WeatherRepository extends JpaRepository<Weather, Long>{
		
		//public Weather findByfullname(String name);
		
		public Optional<Weather> findByDate(String date);

	}

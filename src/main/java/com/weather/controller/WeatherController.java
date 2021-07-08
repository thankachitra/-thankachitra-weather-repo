/**
 * A controller component to fetch/update/delete weather data
 * The controller takes the API request first
 *
 * @author ThankaChitra Krishnan
 * 
 */
package com.weather.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weather.entity.Weather;
import com.weather.service.WeatherServiceInterface;

@RestController
public class WeatherController {

	//auto wired the weather service class to access its methods
	@Autowired
	private WeatherServiceInterface weatherInterface;

	@GetMapping("/weather")
	@ResponseBody
	public ResponseEntity<List<Weather>> readAllWeather() {
		List<Weather> weatherList= new ArrayList<Weather>();
		weatherList=weatherInterface.readAllWeather();
		return ResponseEntity.
				status(HttpStatus.OK).
				cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)). /// enable browser caching  
				body(weatherList);	
}

	@GetMapping("/weather/{dateFilter}")
	public ResponseEntity<List<Weather>> readWeatherByDate(@PathVariable("dateFilter") Date dateFilter) {
		List<Weather> weatherList= new ArrayList<Weather>();
		weatherList=weatherInterface.readWeatherByDate(dateFilter);
		HttpHeaders responseHeaders = new HttpHeaders(); 
		return ResponseEntity.ok().headers(responseHeaders).body(weatherList);
	}

	@PostMapping(value="/weather")
	public ResponseEntity<Weather> addWeather(@RequestBody Weather weather) {
		Weather persistedWeather = weatherInterface.addWeather(weather);
		return ResponseEntity.status(HttpStatus.CREATED).body(persistedWeather);
	}
	
	@DeleteMapping("/erase")
	public ResponseEntity<String> eraseAllWeather() {
		weatherInterface.eraseAllWeather();
		return ResponseEntity.status(HttpStatus.CREATED).body("Records are deleted");	
	}

	@GetMapping("/weather/pageable")
	Page<Weather> pageableListing(Pageable pageable) {
		return weatherInterface.listPageable(pageable);
	}		
}


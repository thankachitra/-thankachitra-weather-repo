/**
* A controller component to fetch/update/delete weather data
* The controller takes the API request first
*
* @author ThankaChitra Krishnan
* 
*/
package com.weather.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weather.entity.Weather;
import com.weather.service.WeatherService;

@RestController
public class WeatherController{
	
//	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
	
	@Autowired
    private WeatherService weatherService;
	
	/* Returning all the weather data */
	@GetMapping("/weather")
	@ResponseBody
	public ResponseEntity<List<Weather>> readAllWeather() throws Exception {
		List<Weather> list= new ArrayList<Weather>();
		list=weatherService.readAllWeatherData();
		HttpHeaders responseHeaders = new HttpHeaders();
		return ResponseEntity.ok().headers(responseHeaders).body(list);	
	}
	
	/* Returning all the weather filtered by date */
	@GetMapping("/weather/{date}")
	public void readByDate(String dateFilter){
		weatherService.filterByDate(dateFilter);
	}
	
	@PostMapping(value="/weather")//,
	public ResponseEntity<Weather> createWeather(@RequestBody Weather weather) throws Exception{
		Weather persistedWeather = weatherService.create(weather);
		HttpHeaders responseHeaders = new HttpHeaders();
		return ResponseEntity.ok().headers(responseHeaders).body(persistedWeather);	
	}
	
	/* delete all weather data */
	@DeleteMapping("/erase")
	public void deleteWeather(){
		weatherService.eraseAll();
		
	}
}


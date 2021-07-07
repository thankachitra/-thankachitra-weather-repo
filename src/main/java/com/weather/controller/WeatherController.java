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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.weather.service.WeatherService;

@RestController
public class WeatherController{
	
//	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
	
	//autowired the weather service class to access its methods
	@Autowired
    private WeatherService weatherService;
	
	// Fetch all the stored weather data from h2 data store 
	@GetMapping("/weather")
	@ResponseBody
	public ResponseEntity<List<Weather>> readAllWeather() {
		try {
			List<Weather> list= new ArrayList<Weather>();
			
			list=weatherService.readAllWeather();
						
			HttpHeaders responseHeaders = new HttpHeaders();
			
			// return HTTP Response code 200=HttpStatus.OK if the records are fetched successfully 
			return ResponseEntity.status(HttpStatus.OK).body(list);	
			//return ResponseEntity.ok().headers(responseHeaders).body(list);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	//Returning all the weather filtered by date
	@GetMapping("/weather/{dateFilter}")
	public ResponseEntity<List<Weather>> readByDate(@PathVariable Date dateFilter){
		try
		{
		List<Weather> list= new ArrayList<Weather>();
		list=weatherService.filterWeatherByDate(dateFilter);
		HttpHeaders responseHeaders = new HttpHeaders();
		return ResponseEntity.ok().headers(responseHeaders).body(list);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		}
	}
	
	// Adding new weather data
	@PostMapping(value="/weather")
	public ResponseEntity<Weather> createWeather(@RequestBody Weather weather) {
		try{
			Weather persistedWeather = weatherService.addWeather(weather);
			if (persistedWeather!=null) {
				System.out.println(" persistedWeather is not null" +persistedWeather.toString());
				// return HTTP Response code 201=HttpStatus.CREATED when the weather is created successfully
				return ResponseEntity.status(HttpStatus.CREATED).body(persistedWeather);
			}
			else {
				System.out.println("persistedWeather is  null");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(weather);
			}

		}
		catch(Exception e){ 
			// return HTTP Response code 400=HttpStatus.BAD_REQUEST when the weather record is already exists with the same id
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(weather);
		}
		
	}
	
	//Delete all weather data and return response code 200 =HttpStatus.CREATED
	@DeleteMapping("/erase")
	public ResponseEntity<String> deleteWeather(){
		try{
		weatherService.eraseAllWeather();
		return ResponseEntity.status(HttpStatus.CREATED).body("Records are deleted");	
		}
		catch(Exception e){ 
			// return HTTP Response code 400=HttpStatus.BAD_REQUEST when the weather record is already exists with the same id
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR in DELETING");
		}
	}
}


/**
 * A controller component to fetch/update/delete weather data
 * The controller takes the API request first
 *
 * @author ThankaChitra Krishnan
 * 
 */
package com.weather.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weather.entity.Weather;
import com.weather.service.WeatherServiceInterface;

@RestController
public class WeatherController {

	//auto wired the weather service class to access its methods
	@Autowired
	private WeatherServiceInterface weatherInterface;

	/*
	 * @RequestMapping(value="/weather/{dateFilter}", method = RequestMethod.GET)
	 * public ResponseEntity<List<Weather>> readWeatherByDate(
	 * 
	 * @RequestParam(required=false,name="filter") Date filter ,
	 * 
	 * @PathVariable("dateFilter") Date dateFilter ) {
	 * 
	 * List<Weather> weatherList= new ArrayList<Weather>();
	 * weatherList=weatherInterface.readWeatherByDate(dateFilter); HttpHeaders
	 * responseHeaders = new HttpHeaders(); return
	 * ResponseEntity.ok().headers(responseHeaders).body(weatherList); }
	 */
	
	/*@GetMapping("/weather")
	@ResponseBody
	public ResponseEntity<List<Weather>> readAllWeather(@RequestParam(required=false, name="date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date dateFilter ) {
	
		List<Weather> weatherList= new ArrayList<Weather>();
		System.out.println("aaaaaaaa "+dateFilter);
	//	weatherList=weatherInterface.readAllWeather();
		weatherList=weatherInterface.readWeatherByDate(dateFilter);
		return ResponseEntity.
				status(HttpStatus.OK).
				cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)). /// enable browser caching  
				body(weatherList);	
} */
	
	
	@GetMapping("/weather")
	@ResponseBody
	public ResponseEntity<List<Weather>> readAllWeather(@RequestParam(required=false, name="date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	String dateFilter ) throws ParseException {
		
		List<Weather> weatherList= new ArrayList<Weather>();

		//	weatherList=weatherInterface.readAllWeather();
		System.out.println(" 11 filterByDate "+ dateFilter);
		
		if (dateFilter!=null)
			weatherList=weatherInterface.readWeatherByDate(dateFilter);
		else
			weatherList=weatherInterface.readAllWeather();
		return ResponseEntity.
				status(HttpStatus.OK).
				cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)). /// enable browser caching  
				body(weatherList);	
} 
	
	

	@PostMapping(value="/weather")
	public ResponseEntity<Weather> addWeather(@RequestBody Weather weather) {
		Weather persistedWeather = weatherInterface.addWeather(weather);
		return ResponseEntity.status(HttpStatus.CREATED).body(persistedWeather);
	}
	
	@DeleteMapping("/erase")
	public ResponseEntity<String> eraseAllWeather() {
		weatherInterface.eraseAllWeather();
		return ResponseEntity.status(HttpStatus.OK).body("Records are deleted");	
	}

	@GetMapping("/weather/pageable")
	Page<Weather> pageableListing(Pageable pageable) {
		return weatherInterface.listPageable(pageable);
	}		
}


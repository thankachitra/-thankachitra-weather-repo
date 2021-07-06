/**
* A Service component
*
* @author ThankaChitra Krishnan
* 
*/

package com.weather.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.weather.entity.Weather;
import com.weather.repository.WeatherRepository;


@Component
public class WeatherService {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

	@Autowired
	private WeatherRepository weatherRepository;
	
	public  List<Weather>  readAllWeatherData() throws Exception
	{
		logger.info(" readWeatherAllData");
		List<Weather> weatherList =weatherRepository.findAll();
		return weatherList;
	}
	
	public Optional<Weather> filterByDate(String filterByDate)
	{
		logger.info("weather filterByDate " +filterByDate);
		Optional<Weather> weatherList =weatherRepository.findByDate(filterByDate);
		return weatherList;
	}
	
	public Weather create(Weather weatherObj) throws Exception
	{
		logger.info("adding a new weather data" +weatherObj);
		
	/*	SimpleDateFormat dateformat = new SimpleDateFormat("MMM d, yyyy HH:mm:ss",
		        Locale.ENGLISH);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String strDate = sdf.format(now);
		
		
		Weather weatherObject1= new Weather();
		Location locationObject1= new Location(
		36.1189f,-86.6892f,"Palo Alto","California");
		
		
		weatherObject1.setDate(new Date());
		
		weatherObject1.setLocation(locationObject1);
		/*	weatherObject1.setTemperature(new Double[]{
			37.3f, 36.8f, 36.4f, 36.0f, 35.6f, 35.3f, 35.0f, 34.9f, 35.8f, 
			38.0f, 40.2f, 42.3f, 43.8f, 44.9f, 45.5f, 45.7f, 44.9f, 43.0f, 41.7f, 40.8f, 39.9f,
			39.2f, 38.6f, 38.1f
			});*/
		
	//	weatherObject1.setTemperature(new Double[]{37.3, 36.8});
	//	weatherObject1.setTemperature(values);*/
		Weather persistentWeather =weatherRepository.save(weatherObj);
		
		return persistentWeather;
	}
	
	
	public void eraseAll()
	{
		logger.info("weather eraseAll ");
		weatherRepository.deleteAll();
	}
}






/**
* A Service component for weather data
*
* @author ThankaChitra Krishnan
* 
*/

package com.weather.service;


import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
		logger.info("readWeatherAllData");
		List<Weather> weatherList =weatherRepository.findAll();
		return weatherList;
	}
	
	public List<Weather> filterByDate(Date filterByDate)
	{
		logger.info("weather filterByDate " +filterByDate);
		List<Weather> weatherList =weatherRepository.findByDate(filterByDate);
		return weatherList;
	}
	
	public Weather create(Weather weatherObj) throws Exception
	{
		logger.info("adding a new weather data" +weatherObj);
		Weather persistentWeather =weatherRepository.save(weatherObj);
		return persistentWeather;
	}
	
	public void eraseAll()
	{
		logger.info("weather eraseAll ");
		weatherRepository.deleteAll();
	}
}






/**
* A Service component
*
* @author ThankaChitra Krishnan
* 
*/

package com.weather.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.weather.entity.Weather;
import com.weather.repository.WeatherRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Component
public class WeatherService {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

	// autowired the weather repository class to perform DB operations EX. findAll, findByDate, save and deleteAll on weather table
	@Autowired
	private WeatherRepository weatherRepository;
	
	public List<Weather> readAllWeather() {
		logger.info("called readWeatherAllData service Method");
		List<Weather> weatherList = new ArrayList<Weather>();
     
		//sort the result based on id value , this is one of the requirement
		Iterable<Weather> weatherIterator=weatherRepository.findAll(Sort.by("id").ascending());
		weatherIterator.forEach(weatherList::add);
		return weatherList;
	}
	
	public List<Weather> filterWeatherByDate(Date filterByDate) {
		logger.info("called weather filterByDate " +filterByDate);
		List<Weather> weatherList =weatherRepository.findByDate(filterByDate);
		return weatherList;
	}
	
	public Weather addWeather(Weather weatherObj) {
		Weather persistentWeather = null;
		logger.info("called addWeather service Method" +weatherObj + weatherObj.getId());
		if (!weatherRepository.existsById(weatherObj.getId())) {
			logger.info("weather obj Id "+ weatherObj.getId() +" not exists. create weather Record");
			persistentWeather =weatherRepository.save(weatherObj);
			logger.info("weather record created" +persistentWeather);
		}
		else  {
			logger.info("weather obj Id "+ weatherObj.getId() +" already exists. respond with statuc code 400 BAD REQUEST ");
		}
		return persistentWeather;
	}
	
	public void eraseAllWeather() {
		try {
		logger.info("called  eraseAllWeather service method ");
		weatherRepository.deleteAll();
		logger.info("finished  eraseAllWeather service method ");
		}
		catch(Exception e) {
		logger.debug(e.toString());
		}
	}

	public Page<Weather> listPageable(Pageable pageable) {
		return weatherRepository.findAll(pageable);
	}	
		
}






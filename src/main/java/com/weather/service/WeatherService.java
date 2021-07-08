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
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weather.entity.Weather;
import com.weather.exception.CustomWeatherException;
import com.weather.repository.WeatherRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Component
public class WeatherService implements WeatherServiceInterface{
	

	// auto wired 
	@Autowired
	private WeatherRepository weatherRepository;
	
	public List<Weather> readAllWeather() {
		List<Weather> weatherList = new ArrayList<Weather>();
    	Iterable<Weather> weatherIterator=weatherRepository.findAll(Sort.by("id").ascending());
		weatherIterator.forEach(weatherList::add);
		return weatherList;
	}
	
	public List<Weather> readWeatherByDate(String filterByDate) throws ParseException {
	
		boolean checkFormat;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		if (filterByDate.toString().isEmpty()) {
			throw new CustomWeatherException("400","Input date is empty");
		}
		
		checkFormat=filterByDate.matches("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))");
		
		if (!checkFormat) {
			throw new CustomWeatherException("400","Input date format is not valid");
		}
		
		List<Weather> weatherList =weatherRepository.
				findByDate(format.parse(filterByDate));
		return weatherList;
	}
	
	public Weather addWeather(Weather weatherObj) {
		
		Weather persistentWeather;
		long weatherId=weatherObj.getId();
		if ( Objects.isNull(weatherId)){
			throw new CustomWeatherException("400","Id value is empty");
		}
		if (weatherRepository.existsById(weatherId)) {
			throw new CustomWeatherException("400","The record with Id " + weatherId +" already exists. try giving valid ID value");
		}
		persistentWeather =weatherRepository.save(weatherObj);
		return persistentWeather;
	}
	
	public void eraseAllWeather() {
		weatherRepository.deleteAll();
	}

	public Page<Weather> listPageable(Pageable pageable) {
		return weatherRepository.findAll(pageable);
	}	
		
}






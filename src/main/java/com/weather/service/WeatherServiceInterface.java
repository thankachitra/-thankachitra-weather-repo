package com.weather.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.weather.entity.Weather;

public interface WeatherServiceInterface {
	public List<Weather> readAllWeather() ;
	public List<Weather> readWeatherByDate(String filterByDate) throws ParseException;
	public Weather addWeather(Weather weatherObj);
	public void eraseAllWeather();
	Page<Weather> listPageable(Pageable pageable) ;

}

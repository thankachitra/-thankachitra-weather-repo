package com.weather.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.weather.entity.Weather;

public interface WeatherServiceInterface {
	public List<Weather> readAllWeather() ;
	public List<Weather> readWeatherByDate(Date filterByDate);
	public Weather addWeather(Weather weatherObj);
	public void eraseAllWeather();
	Page<Weather> listPageable(Pageable pageable) ;

}

/**
 * spring boot application for weather reports , rest services for CRUD operations
 *
 * @author ThankaChitra Krishnan
 * * 
 */

package com.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherrestApplication {
	private static final Logger logger = 
			LoggerFactory.getLogger(WeatherrestApplication.class);


	public static void main(String[] args) {
	    System.out.println("aaaaaaaaa " + System.getProperty("java.io.tmpdir"));
		logger.info("aaaaaaaaa " +System.getProperty("java.io.tmpdir"));
		SpringApplication.run(WeatherrestApplication.class, args);
	}
}

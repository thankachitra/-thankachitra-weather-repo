/**
* A weather controller advice for handing  custom exceptions and java generic exception.Its purpose is to provide 
* Meaningful message to the client when there is an error
*
* @author ThankaChitra Krishnan
* 
*/

package com.weather.advice;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.weather.exception.CustomWeatherException;

@ControllerAdvice
public class WeatherControllerAdvice {

	@ExceptionHandler(CustomWeatherException.class)
	public ResponseEntity<String> handleEmptyInput(CustomWeatherException emptyInputException )
	{
		return new ResponseEntity<String>(emptyInputException.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException exception )
	{
		return new ResponseEntity<String>("No value is present in DB, Please change your request input ", HttpStatus.NOT_FOUND);
	}


}

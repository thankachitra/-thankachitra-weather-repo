/**
* A weather controller advice for handing  custom exceptions and java generic exception.Its purpose is to provide 
* Meaningful message to the client when there is an error
*
* @author ThankaChitra Krishnan
* 
*/

package com.weather.advice;

import java.text.ParseException;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

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
		
	 @ExceptionHandler(HttpMessageNotReadableException.class)
	 public ResponseEntity<String>  handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
		 System.out.println(" inside HttpMessageNotReadableException ");
		 return new ResponseEntity<String>("Check the JSON Input for weather,it is not in correct format", HttpStatus.BAD_REQUEST);
	 }

	 @ExceptionHandler(ParseException.class)
		public ResponseEntity<String> handleDateFomatException(ParseException exception )
		{
			return new ResponseEntity<String>("Date parse Exception.date format should be \"yyyy-MM-dd\"" +exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
}

/**
* A weather entity to be stored in h2 db
*
* @author ThankaChitra Krishnan
* 
*/
package com.weather.entity;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

//defining class name as Table name  , A table with the name Weather will be created in H2 */
@Entity
public class Weather {

	//define primary key as id , auto generated value 
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	private Long id;

	//define date column name 
	@Column(name = "date")
	@Temporal(value = TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	//define temperature as column name
	@Column(name = "temperature")
	private Double[] temperature;
  
	//define Location as an embedded object in the weather table
	@Embedded
	@Column(name = "location")
	private Location location;

	public Long getId(){
		return id;}

	public void setId(Long id){
		this.id = id;}
	
	public Date getDate(){
		return this.date;}
  
	public void setDate(Date date){ 
		this.date = date;}
	
	public Double[] getTemperature(){
		return this.temperature;}
	
	public void setTemperature(Double[] temperature){ 
		this.temperature = temperature;}

	public Location getLocation(){
		return this.location;}
	
	public void setLocation(Location location){ 
		this.location = location;} 
  
}


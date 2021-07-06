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
import org.json.JSONObject;


@Entity
public class Weather {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @Column(name = "ID")
  private Long id;

  @Temporal(value = TemporalType.DATE)
  @Column(name = "date")
  private Date date;

  
@Column(name = "temperature")
  private Double[] temperature;
  
 @Embedded
 @Column(name = "location")
 private Location location;

  public Long getId() {
		return id;
	}

	public void setId(Long id) {
		id = id;
	}
	
  public Date getDate() {
  	return this.date; }
  
  public void setDate( Date date ) { 
  	this.date = date; }
  
  public Double[] getTemperature() {
  	return this.temperature; }
  
  public void setTemperature(Double[] temperature ) { 
  	this.temperature = temperature; }

 public Location getLocation() {
  	return this.location; }
  
  public void setLocation( Location location ) { 
  	this.location = location; } 
  
}


/**
* A Location embedded entity to be stored in h2 db
*
* @author ThankaChitra Krishnan
* 
*/
package com.weather.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Location {

	@Column(name = "lat") //latitude //
	  private float lat;

	  @Column(name = "lon") //longitude /
	  private float lon;
	  
	  @Column(name = "city")
	  private String city;
	  
	  @Column(name = "state")
	  private String state;
	  
	  public float getLat(){
		  return lat;}
		
	  public void setLat(float lan){
		  this.lon= lon;}
	  
	  public float getLan(){
		  return lon;}
	  
	  public void setLan(float lon){
		  this.lon=lon;}
		
	  public String getCity(){
		  return city;}
	
	  public void setCity(String city){
			this.city= city;}
	  
	  public String getState(){
			return state;}
	  
	  public void seState(String state){
			this.state= state;}
	  
	  public Location(float lat,float lon, String city, String state){
			this.lat=lat;
			this.lon=lon;
			this.city=city;
			this.state=state;}
	  
	  public Location(){
			/* default constructor */ /* @Embedded annotation expects to be created or throwing error */}
}

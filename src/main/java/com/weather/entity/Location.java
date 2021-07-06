package com.weather.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Location {
	
/*	 float lat; //latitude //*has to be changed to float -m 6 to 7 decimal digits
	 float lon; //longitude /* double allows 15 decimal digits
	 String city,state; */
		
	  @Column(name = "lat")
	  private float lat;

	  @Column(name = "lon")
	  private float lon;
	  
	  @Column(name = "city")
	  private String city;
	  
	  @Column(name = "state")
	  private String state;
	  
	  
	  public float getLat()
		{
			return lat;
		}
		
		public void setLat()
		{
			this.lon= lon;}
		
		
		public float getLan()
		{
			return lon;}
		
		public String getCity()
		{
			return city;}
		
		
		public void setCity()
		{
			this.city= city;}
		
		public String getState()
		{
			return state;}
		
		
		public void seState()
		{
			this.state= state;}
		
		public Location(float lat,float lon, String city, String state)
		{
			this.lat=lat;
			this.lon=lon;
			this.city=city;
			this.state=state;
			
		}
		public Location()
		{
			/* default constructor */ /* @Embedded annotation expects to be created or throwing error */
		}
		
	
}

package co.grandcircus.CO2Competition.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;


//note: I'm keeping end and Start locations handy for now, just in case 
//adding a map will require them
public class StartLocation {
	
	@JsonProperty("lat")
	long lat;
	
	@JsonProperty("lng")
	long lng;

	
	
}

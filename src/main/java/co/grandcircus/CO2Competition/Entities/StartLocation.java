package co.grandcircus.CO2Competition.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StartLocation {
	
	@JsonProperty("lat")
	long lat;
	
	@JsonProperty("lng")
	long lng;

	
	
}

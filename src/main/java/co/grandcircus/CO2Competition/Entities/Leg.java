package co.grandcircus.CO2Competition.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
/*
 * Step 3: We parse the individual legs, were most of the real data is stored
 * 
 */

public class Leg {
	//Each leg has one distance object..
	@JsonProperty("distance")
	Distance distance;

	//One start location object(latitude and longitude)..
	@JsonProperty("start_location")
	StartLocation startLocation;
	
	//One end location object(ditto)..
	@JsonProperty("end_location")
	EndLocation endLocation;
	
	
	//And a string each for start and end address
	@JsonProperty("start_address")
	String startAddress;
	
	@JsonProperty("end_address")
	String endAddress;

	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}



	public StartLocation getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(StartLocation startLocation) {
		this.startLocation = startLocation;
	}

	public EndLocation getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(EndLocation endLocation) {
		this.endLocation = endLocation;
	}

		
	public String getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	public String getEndAddress() {
		return endAddress;
	}

	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}

	@Override
	public String toString() {
		return "Leg [distance=" + distance + ", startLocation=" + startLocation + ", endLocation=" + endLocation
				+ ", startAddress=" + startAddress + ", endAddress=" + endAddress + "]";
	}

	


}

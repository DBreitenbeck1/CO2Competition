package co.grandcircus.CO2Competition.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Leg {
	@JsonProperty("distance")
	Distance distance;
	
	@JsonProperty("duration")
	Duration duration;
	
	@JsonProperty("start_location")
	StartLocation startLocation;
	
	@JsonProperty("end_location")
	EndLocation endLocation;
	
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

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
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
		return "Leg [distance=" + distance + ", duration=" + duration + ", startLocation=" + startLocation
				+ ", endLocation=" + endLocation + "]";
	}
	
	


}

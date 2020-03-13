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

	@Override
	public String toString() {
		return "Leg [distance=" + distance + ", duration=" + duration + ", startLocation=" + startLocation
				+ ", endLocation=" + endLocation + "]";
	}
	
	

}

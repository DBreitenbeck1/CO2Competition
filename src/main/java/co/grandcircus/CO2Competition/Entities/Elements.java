package co.grandcircus.CO2Competition.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Elements {
	
	
	
	@JsonProperty("distance")
	Distance distance;
	
	@JsonProperty("duration")
	Duration duration;

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

	@Override
	public String toString() {
		return "Element [distance=" + distance + ", duration=" + duration + "]";
	}
	
	
	

}

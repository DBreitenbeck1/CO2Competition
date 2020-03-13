package co.grandcircus.CO2Competition.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Route {
	@JsonProperty("legs")
	List<Leg> legs;

	public List<Leg> getLegs() {
		return legs;
	}

	public void setLegs(List<Leg> legs) {
		this.legs = legs;
	}

	@Override
	public String toString() {
		return "Route [legs=" + legs + "]";
	}
	
	

}

package co.grandcircus.CO2Competition.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


/*
 * Step 2: We parse the individual routes returned in the Search Result 
 * (usually not more than one)
 */
public class Route {
	
	//Each route has one or more legs, depending on whether waypoints are used
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

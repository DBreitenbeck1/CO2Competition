package co.grandcircus.CO2Competition.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
/*
 * Step 1: Parses the total search result from using the API
 */
public class SearchResult {
	
	//The search result returns a destination address...
	@JsonProperty("destination_addresses")
	List<String> destinationAddresses;
	
	//And an origin address...
	@JsonProperty("origin_addresses")
	List<String> originAddresses;
	
	
	//And a list of routes
	@JsonProperty("routes")
	List<Route> routes;

	public List<String> getDestinationAddresses() {
		return destinationAddresses;
	}

	public void setDestinationAddresses(List<String> destinationAddresses) {
		this.destinationAddresses = destinationAddresses;
	}

	public List<String> getOriginAddresses() {
		return originAddresses;
	}

	public void setOriginAddresses(List<String> originAddresses) {
		this.originAddresses = originAddresses;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
	
	

	@Override
	public String toString() {
		return "SearchResult [destinationAddresses=" + destinationAddresses + ", originAddresses=" + originAddresses
				+ ", routes=" + routes + "]";
	}

	
	
	

}

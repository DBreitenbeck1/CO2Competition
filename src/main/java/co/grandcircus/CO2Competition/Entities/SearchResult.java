package co.grandcircus.CO2Competition.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResult {
	
	@JsonProperty("destination_addresses")
	List<String> destinationAddresses;
	
	@JsonProperty("origin_addresses")
	List<String> originAddresses;
	
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

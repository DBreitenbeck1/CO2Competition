package co.grandcircus.CO2Competition;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.CO2Competition.Entities.Distance;
import co.grandcircus.CO2Competition.Entities.Leg;
import co.grandcircus.CO2Competition.Entities.Route;
import co.grandcircus.CO2Competition.Entities.SearchResult;

@Component
public class ApiService {

	
	@Value("${api_key}")
	String apiKey;
	
	
	
	private RestTemplate rt;

	{
		ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
			request.getHeaders().add(HttpHeaders.USER_AGENT, "JMJ");
			return execution.execute(request, body);
		};
		rt = new RestTemplateBuilder().additionalInterceptors(interceptor).build();
	}

	public SearchResult getResult(String startAddress, String destAddress){
		String url = ("https://maps.googleapis.com/maps/api/directions/json?origin="
				+ startAddress + "&destination=" + destAddress + "&key=" + apiKey);
		SearchResult result = rt.getForObject(url, SearchResult.class);
		return result;
	}
	
	// Overloaded for Midway Point
	public SearchResult getResult(String startAddress, String midway, String destAddress) throws IllegalArgumentException{
		String url = ("https://maps.googleapis.com/maps/api/directions/json?origin="
				+ startAddress + "&destination=" + destAddress
				+ "&waypoints=" + midway
				+ "&key=" + apiKey);
		SearchResult result = rt.getForObject(url, SearchResult.class);
		return result;
	}
	
	// Overloaded for Multiple waypoints
		public SearchResult getMultipleResults(String startAddress, List<String> stops, String destAddress) throws IllegalArgumentException{
			String midway="";
			for(String address:stops) {
				midway+=address+"|";
			}
			String url = ("https://maps.googleapis.com/maps/api/directions/json?origin="
					+ startAddress + "&destination=" + destAddress
					+ "&waypoints=" + midway
					+ "&key=" + apiKey);
			SearchResult result = rt.getForObject(url, SearchResult.class);
			return result;
		}


	// Overloaded to allow user to pick which leg they want
	public Distance getDistance(SearchResult result, Integer index) {
		Route route = result.getRoutes().get(0);
		Leg leg = route.getLegs().get(index);
		return leg.getDistance();
	}
	
	// Overloaded, returns aggregate distance of full trip
	public Distance getDistance(SearchResult result){
		String dist;
		Double distDouble = 0.0;
		Long distLong = 0L;
		
		Route routes = result.getRoutes().get(0);
		List<Leg> legs = routes.getLegs();
		for (Leg leg : legs) {
			distLong += leg.getDistance().getValue();

			dist = leg.getDistance().getText();
			dist = dist.substring(0, dist.length() - 3);
			distDouble += Double.parseDouble(dist);
		}
		
		dist = distDouble.toString() + " mi";
		Distance distance = new Distance();
		distance.setText(dist);
		distance.setValue(distLong);
		return distance;
	}
	
	public List<Route> getRoutes(SearchResult result){
		return result.getRoutes();
	}
	
	//Returns start address
	public String getStart(SearchResult result, Integer index) throws IllegalArgumentException {
		List<Route> routes = result.getRoutes();
		try {
			Route r = routes.get(0);	
			Leg leg = r.getLegs().get(index);
			return leg.getStartAddress();
		} catch (IndexOutOfBoundsException iae) {
			iae.printStackTrace();
			throw new IllegalArgumentException("No Results Found");
		}
	}

	//Returns destination address
	public String getDest(SearchResult result, Integer index) {
		List<Route> routes = result.getRoutes();
		Route r = routes.get(0);	
		Leg leg = r.getLegs().get(index);
		return leg.getEndAddress();	
	}
	
	
	
	
}

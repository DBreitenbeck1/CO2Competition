package co.grandcircus.CO2Competition;

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
	
	public Distance getDistance(String startAddress, String destAddress){
		String url = ("https://maps.googleapis.com/maps/api/directions/json?origin="
				+ startAddress+ "&destination=" +destAddress+"&key="+apiKey);
		SearchResult result = rt.getForObject(url, SearchResult.class);
			System.out.println(result.toString());
		List<Route> routes = result.getRoutes();
		Route r;
		try {
			r = routes.get(0);
			} catch(Exception e) {
				System.out.println("No route found");
				return null;
			}
		Leg leg = r.getLegs().get(0);
			System.out.println(leg.toString());
		return leg.getDistance();
		
	}
	
	public List<Route> getRoutes(String startAddress, String destAddress){
		String url = ("https://maps.googleapis.com/maps/api/directions/json?origin="
				+ startAddress+ "&destination=" +destAddress+"&key="+apiKey);
		SearchResult result = rt.getForObject(url, SearchResult.class);
			System.out.println(result.toString());
		return result.getRoutes();
		
	}
	

	public String getStart(String startAddress, String destAddress) {
		String url = ("https://maps.googleapis.com/maps/api/directions/json?origin="
				+ startAddress+ "&destination=" +destAddress+"&key="+apiKey);
		SearchResult result = rt.getForObject(url, SearchResult.class);
			System.out.println(result.toString());
		List<Route> routes = result.getRoutes();
		Route r = routes.get(0);	
			System.out.println(r.getLegs().size());
		Leg leg = r.getLegs().get(0);
			System.out.println(leg.toString());
		return leg.getStartAddress();
		
	}
//	
//	public String getDest(String startAddress, String destAddress) {
//		String url = ("https://maps.googleapis.com/maps/api/directions/json?origin="
//				+ startAddress+ "&destination=" +destAddress+"&key="+apiKey);
//		SearchResult result = rt.getForObject(url, SearchResult.class);
//			System.out.println(result.toString());
//		List<Route> routes = result.getRoutes();
//		Route r = routes.get(0);	
//			System.out.println(r.getLegs().size());
//		Leg leg = r.getLegs().get(0);
//			System.out.println(leg.toString());
//		return leg.getEndAddress();
//		
//	}
	
	
	public SearchResult getResult(String startAddress, String destAddress){
		String url = ("https://maps.googleapis.com/maps/api/directions/json?origin="
				+ startAddress+ "&destination=" +destAddress+"&key="+apiKey);
		SearchResult result = rt.getForObject(url, SearchResult.class);
		return result;
	}
	

	public String getDest(SearchResult result) {
		List<Route> routes = result.getRoutes();
		Route r = routes.get(0);	
			System.out.println(r.getLegs().size());
		Leg leg = r.getLegs().get(0);
			System.out.println(leg.toString());
		return leg.getEndAddress();	
	}
	
	
}

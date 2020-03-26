package co.grandcircus.CO2Competition;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.grandcircus.CO2Competition.Entities.Distance;
import co.grandcircus.CO2Competition.Entities.SearchResult;
import co.grandcircus.CO2Competition.Objects.Company;
import co.grandcircus.CO2Competition.Objects.Employee;
import co.grandcircus.CO2Competition.Repos.CompanyRepo;

@Component
public class RouteCalculator {

	@Autowired
	ApiService apiServe;
	@Autowired
	HttpSession sesh;
	@Autowired
	CompanyRepo cRepo;

	public List<Distance> getDistances(List<Employee> employeeList, String method) {
		Employee employee = (Employee) sesh.getAttribute("employee");

		List<Distance> distances = new ArrayList<>();
		SearchResult result = new SearchResult();
		String employeeAddress = employee.getAddress();

		// find employee that work for this company and calculate their distance
		// from home to work and to each others house
		if (method.equals("fromUser")) {
			for (Employee e : employeeList) {
				result = apiServe.getResult(employeeAddress, e.getAddress());
				Distance distance = apiServe.getDistance(result);
				if (distance != null) {
				distances.add(distance);
				}
			}
		} else if (method.equals("fromWork")) {
			Company company = cRepo.findByName(employee.getCompany().getName());
			for (Employee e : employeeList) {
				result = apiServe.getResult(e.getAddress(), company.getAddress());
				Distance distance = apiServe.getDistance(result);
				if (distance != null) {
				distances.add(distance);
				}
//				distances.add(apiServe.getDistance(result));
			}
		}
		return distances;
	}
	
	public Distance getDistanceSingle() {
		// Get Employee from Session
		Employee employee = (Employee) sesh.getAttribute("employee");

		// gets API results for route between employee and company
		SearchResult result = apiServe.getResult(employee.getAddress(), employee.getCompany().getAddress());
		
		// gets distance of route and returns as distance item
		
		return apiServe.getDistance(result);
	}
}

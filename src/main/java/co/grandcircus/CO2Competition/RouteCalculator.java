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
				// Can we add some validation here to check if the driver is farther away from
				// the passenger than the company?
				// Or within a certain radius (ie. 10 miles?) -- Sam

				result = apiServe.getResult(employeeAddress, e.getAddress());
				distances.add(apiServe.getDistance(result));
			}
		} else if (method.equals("fromWork")) {
			Company company = cRepo.findByName(employee.getCompany().getName());
			for (Employee e : employeeList) {
				result = apiServe.getResult(e.getAddress(), company.getAddress());
				distances.add(apiServe.getDistance(result));
			}
		}
		return distances;
	}
}

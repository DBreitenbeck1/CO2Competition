package co.grandcircus.CO2Competition;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.CO2Competition.Entities.Distance;
import co.grandcircus.CO2Competition.Entities.SearchResult;
import co.grandcircus.CO2Competition.Objects.Carpool;
import co.grandcircus.CO2Competition.Objects.Company;
import co.grandcircus.CO2Competition.Objects.Employee;
import co.grandcircus.CO2Competition.Repos.CarpoolRepo;
import co.grandcircus.CO2Competition.Repos.CompanyRepo;
import co.grandcircus.CO2Competition.Repos.EmployeeRepo;

@Component
public class COCalculator {
	
	@Autowired
	private ApiService apiServe;
	
	//Estimations based on average for each car type
	public double smallCar(long meters) {
		double miles = meters/1609.344;
		double emissions = 7.08*miles;
		emissions = (int)(Math.round(emissions*100))/100.00;
		return emissions;
	}
	
	public double medCar(double meters) {
		double miles = meters/1609.344;
		double emissions = 13.2*miles;
		emissions = (int)(Math.round(emissions*100))/100.00;
		return emissions;
	}

	public double SUV(double meters) {
		double miles = meters/1609.344;
		double emissions = 18.84*miles;
		emissions = (int)(Math.round(emissions*100))/100.00;
		return emissions;
	}
	
	
	// Overloaded method, takes in start and destination, calculates CO2 that would have been spent, and returns that value as "Savings"
	public double calculateSavings(
			String start,
			String destination
			) {

		// Calculate distance
		SearchResult result = apiServe.getResult(start, destination);
		Distance distance = apiServe.getDistance(result);

		// call calculator method, assume medCar for now
		double CO2Savings = medCar(distance.getValue());

		double score = CO2Savings * 10;
		
		// return savings
		return score;
	}
	
	// Takes in three strings, two start points and one end point, calculates the savings for each, and returns the lower amount
	public Integer calculateDifference(
			String startUser,
			String startPassenger,
			String destination
			) {
		Integer returnSavings;
		
		// Get points
		double savingsUser = calculateSavings(startUser, destination);
		double savingsPassenger = calculateSavings(startPassenger, destination);
		
		//Find lower amount
		if (savingsUser < savingsPassenger) {
			returnSavings = (int) (Math.round(savingsUser * 100) / 100.00);
		} else {
			returnSavings = (int) (Math.round(savingsUser * 100) / 100.00);
		}
		
		return returnSavings;		
	}
	
	
//	public double calculateSavings(
//			String start,
//			String midway,
//			String destination) {
//		// get original spending CO2
//		// get original for each leg
//		SearchResult startToDestination = apiServe.getResult(start, destination);
//		SearchResult midwayToDestination = apiServe.getResult(midway, destination);
//		
//		
//		// get current CO2
//		// subtract and return savings
//		
//		return 0.0;
//	}
	
	@Autowired
	EmployeeRepo emRepo;
	@Autowired
	COCalculator coCal;
	@Autowired
	CompanyRepo coRepo;
	@Autowired
	CarpoolRepo carRepo;
	@PostConstruct
	public void tester() {
		
		String username = "NateRedA";
		String date = "2020-03-20";
		String time = "9:01AM";
		
		// Creates list of carpoolers and adds current user and selected carpooler
		Employee employee = emRepo.findByUsernameIgnoreCase("SirArthurD");
		Employee passenger = emRepo.findByUsernameIgnoreCase(username);
		List<Employee> poolers = Arrays.asList(employee, passenger);
		Company company = employee.getCompany();
//		Company company = coRepo.findById(companyId).orElse(null);

		// Get distances to compare and calculate savings
		Integer score = coCal.calculateDifference(employee.getAddress(), passenger.getAddress(), company.getAddress());

		// Create new Carpool object to save to database
		Carpool carpool = new Carpool();
		carpool.setCompany(company);
		carpool.setDate(date + " " + time);
		carpool.setCo2(score);
		
		// Tie Carpool to employees
		carpool.setEmployees(poolers);
		for (Employee pooler : poolers) {
			pooler.addCarpool(carpool);
//			System.out.println(poolers);
			emRepo.save(pooler);
		}
//		System.out.println(carpool);
		
		
		
		carRepo.save(carpool);
	}

	
}

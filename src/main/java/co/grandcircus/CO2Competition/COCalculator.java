package co.grandcircus.CO2Competition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.grandcircus.CO2Competition.Entities.Distance;
import co.grandcircus.CO2Competition.Entities.SearchResult;
import co.grandcircus.CO2Competition.Objects.Employee;

@Component
public class COCalculator {

	@Autowired
	private ApiService apiServe;

	/*
	 *  Calculations are based on the average MPG of the most popular cars 
	 *  in each category and assuming 19.64 lbs of CO2 burned per gallon of gasoline  
	 */
	public double smallCar(long meters) {
		double miles = meters / 1609.344;
//		System.out.println("Miles: "+ miles);
		double emissions = 0.76 * miles;
		emissions = (int) (Math.round(emissions * 100)) / 100.00;
		return emissions;
	}

	public double medCar(double meters) {
		double miles = meters / 1609.344;
//		System.out.println("Miles: "+ miles);
		double emissions = 0.93 * miles;
		emissions = (int) (Math.round(emissions * 100)) / 100.00;
		return emissions;
	}

	public double truck(double meters) {
		double miles = meters / 1609.344;
//		System.out.println("Miles: "+ miles);
		double emissions = 1.16 * miles;
		emissions = (int) (Math.round(emissions * 100)) / 100.00;
		return emissions;
	}

	// Overloaded method, takes in start and destination, calculates CO2 that would
	// have been spent, and returns that value as "Savings"
	public double calculateSavings(Employee employee) {

		// Calculate distance
		SearchResult result = apiServe.getResult(employee.getAddress(), employee.getCompany().getAddress());
		Distance distance = apiServe.getDistance(result);
		
		// call calculator method, assume medCar for now
		double CO2Savings = 0;
		switch (employee.getVehicleType()) {
		case ("sedan"):
			CO2Savings = smallCar(distance.getValue());
			break;
		case ("suv"):
			CO2Savings = medCar(distance.getValue());
			break;
		case ("truck"):
			CO2Savings = truck(distance.getValue());
			break;
		}
	
		// return savings
		return CO2Savings;
	}
	

	// Takes in three strings, two start points and one end point, calculates the
	// savings for each, and returns the lower amount
	public Double calculateDifference(Employee user, Employee passenger) {
		Double returnSavings;

		// Get points
		double savingsUser = calculateSavings(user);
		double savingsPassenger = calculateSavings(passenger);

		// Find lower amount
		if (savingsUser < savingsPassenger) {
			returnSavings = Math.round(savingsUser * 100) / 100.00;
		} else {
			returnSavings = Math.round(savingsPassenger * 100) / 100.00;
		}

		return returnSavings;
	}

}
package co.grandcircus.CO2Competition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.grandcircus.CO2Competition.Entities.Distance;
import co.grandcircus.CO2Competition.Entities.SearchResult;

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

		// return savings
		return CO2Savings;
	}
	
	// Takes in three strings, two start points and one end point, calculates the savings for each, and returns the lower amount
	public Double calculateDifference(
			String startUser,
			String startPassenger,
			String destination
			) {
		Double returnSavings;
		
		// Get points
		double savingsUser = calculateSavings(startUser, destination);
		double savingsPassenger = calculateSavings(startPassenger, destination);
		
		//Find lower amount
		if (savingsUser < savingsPassenger) {
			returnSavings = Math.round(savingsUser * 100) / 100.00;
		} else {
			returnSavings = Math.round(savingsUser * 100) / 100.00;
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
	

	
}

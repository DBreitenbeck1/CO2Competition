package co.grandcircus.CO2Competition;

public class COCalculator {
	//Estimations based on average for each car type
	
	public static double smallCar(double meters) {
		double miles = meters/1609.344;
		double emissions = 7.08*miles;
		emissions = (int)(Math.round(emissions*100))/100.00;
		return emissions;
	}
	
	
	public static double medCar(double meters) {

		double miles = meters/1609.344;
		double emissions = 13.2*miles;
		emissions = (int)(Math.round(emissions*100))/100.00;
		
		return emissions;
	}

	public static double SUV(double meters) {

		double miles = meters/1609.344;
		double emissions = 18.84*miles;
		emissions = (int)(Math.round(emissions*100))/100.00;
		
		return emissions;
	}
	
	
}

package co.grandcircus.CO2Competition;

public class CalculationService {
	
	public Double calculateCO2(Double miles, String vehicle) {
		Double mpg;
		// source: https://afdc.energy.gov/data/10310
		switch(vehicle) {
		case "car":
			mpg = 23.96;
			break;
		case "suv":
			mpg = 22.04;
		case "truck":
			mpg = 17.4;
			break;
		default:
			mpg = 0.0;
		}
		
		// Source: https://www.eia.gov/environment/emissions/co2_vol_mass.php
		// # CO2 per gallon of gasoline burned
		final Double CO2 = 19.60;
		
		Double gallonsBurned = miles / mpg;
		double CO2Burned = gallonsBurned * CO2;
		CO2Burned = (int)(Math.round(CO2Burned *100))/100.00;
		return CO2Burned;
		
	}

}

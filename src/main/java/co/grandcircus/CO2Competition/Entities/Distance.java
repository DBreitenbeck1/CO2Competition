package co.grandcircus.CO2Competition.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;


/*
 * Step 4: We parse the distance
 * Here is the data we chiefly use for this project
 */
public class Distance {
	
	//This is a String giving the distance in miles to the first decimal:
	//e.g. "35.1 mi"
	@JsonProperty("text")
	String text;
	
	//This is a Long giving the distance in Meters. 
	//e.g. 5567
	
	@JsonProperty("value")
	Long value;

	/*Note: There are 1609.344 Meters in a mile, so to convert value to text,
	 * miles = value/1609.344.  
	 * When restricted to a single decimal, the two numbers should be even.
	 * miles = (int)(math.round(miles*10))/10.0; 
	 */
	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Distance [text=" + text + ", value=" + value + "]";
	}
	
	
	
}

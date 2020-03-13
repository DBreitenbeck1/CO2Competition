package co.grandcircus.CO2Competition.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Duration {
	
	@JsonProperty("text")
	String text;
	
	@JsonProperty("value")
	Long value;
	
	
	
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
		return "Duration [text=" + text + ", value=" + value + "]";
	}
	
	

}

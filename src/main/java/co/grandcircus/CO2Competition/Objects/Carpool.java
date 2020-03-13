package co.grandcircus.CO2Competition.Objects;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Carpool {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long carpoolId;
	// to do change to object date
	private String date;
	private Double co2;

	public Long getCarpoolId() {
		return carpoolId;
	}

	public void setCarpoolId(Long carpoolId) {
		this.carpoolId = carpoolId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getCo2() {
		return co2;
	}

	public void setCo2(Double co2) {
		this.co2 = co2;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	private List<Employee> employees;

}

package co.grandcircus.CO2Competition.Objects;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity

public class Carpool{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long carpoolId;
	// to do change to object date
	private String date;
	private Integer co2;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Company company;
	
	@ManyToMany(mappedBy="carpool", fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Employee> employees;
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

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

	public Integer getCo2() {
		return co2;
	}

	public void setCo2(Integer co2) {
		this.co2 = co2;
	}

	// Added method for consistency principle --Sam
	public boolean addEmployee(Employee employeeToAdd) {
		// prevent endless loop
		if (employees.contains(employeeToAdd)) {
			return false;
		} else {
			//add new user to carpool
			employees.add(employeeToAdd);
			return true;
		}
	}
	
	// added method for consistency principle --Sam
	public boolean removeEmployee(Employee employeeToRemove) {
		// prevent endless loop
		if(!employees.contains(employeeToRemove)) {
			return false;
		} else {
			// If item exists, remove it
			employees.remove(employeeToRemove);
			return true;
		}
	}

	public List<Employee> getEmployees() {
		return employees;
	}
	
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Carpool [carpoolId=" + carpoolId + ", date=" + date + ", co2=" + co2 + ", company=" + company
				+ ", employees=" + employees + "]";
	}



	
	
	
}

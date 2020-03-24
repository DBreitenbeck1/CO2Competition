package co.grandcircus.CO2Competition.Objects;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity

public class Company implements Serializable {

	// Serializable attribute - Allows user to stay logged in when server refreshes -- Sam
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long companyId;

	private String name;
	private String streetAddress;
	private String city;

	public Integer getGoal() {
		return goal;
	}

	public void setGoal(Integer goal) {
		this.goal = goal;
	}

	private String zipCode;

	private Integer goal;

	@OneToMany(mappedBy = "company")
	private List<Employee> employees;

	@OneToMany(mappedBy = "company")
	private List<Carpool> carpool;

	// Added method for consistency principle --Sam
	public boolean addEmployee(Employee employeeToAdd) {
		// prevent endless loop
		if (employees.contains(employeeToAdd)) {
			return false;
		} else {
			// add new user to carpool
			employees.add(employeeToAdd);
			return true;
		}
	}

	// added method for consistency principle --Sam
	public boolean removeEmployee(Employee employeeToRemove) {
		// prevent endless loop
		if (!employees.contains(employeeToRemove)) {
			return false;
		} else {
			// If item exists, remove it
			employees.remove(employeeToRemove);
			return true;
		}
	}

	// Added method for consistency principle --Sam
	public boolean addCarpool(Carpool carpoolToAdd) {
		// prevent endless loop
		if (carpool.contains(carpoolToAdd)) {
			return false;
		} else {
			// add new user to carpool
			carpool.add(carpoolToAdd);
			return true;
		}
	}

	// added method for consistency principle --Sam
	public boolean removeCarpool(Carpool carpoolToRemove) {
		// prevent endless loop
		if (!carpool.contains(carpoolToRemove)) {
			return false;
		} else {
			// If item exists, remove it
			carpool.remove(carpoolToRemove);
			return true;
		}
	}

	public List<Carpool> getCarpool() {
		return carpool;
	}

	public void setCarpool(List<Carpool> carpool) {
		this.carpool = carpool;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getAddress() {
		return this.streetAddress + ", " + this.city + " " + this.zipCode;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAdress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", name=" + name + ", streetAddress=" + streetAddress + ", city="
				+ city + ", zipCode=" + zipCode + ", goal=" + goal + ", employees=" + employees + ", carpool=" + carpool
				+ "]";
	}

}
package co.grandcircus.CO2Competition.Objects;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity

public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long companyId;

	private String name;
	private String streetAddress;
	private String city;

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", name=" + name + ", streetAddress=" + streetAddress + ", city="
				+ city + ", zipCode=" + zipCode + ", goal=" + goal + ", employees=" + employees + ", carpool=" + carpool
				+ "]";
	}

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

}
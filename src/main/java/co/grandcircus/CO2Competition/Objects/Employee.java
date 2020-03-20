package co.grandcircus.CO2Competition.Objects;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity

public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	private String name;
	
	private String username;
	private String password;
	
	private String streetAddress;
	private String city;
	private String zipCode;
	private String vehicleType;
	
	
	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	@ManyToOne
	private Company company;
	
	@ManyToMany
	private List<Carpool> carpool;

	public void addCarpool(Carpool carpool) {
		this.carpool.add(carpool);
	}
	
	public String getAddress() {
	return this.streetAddress+", " +this.city+" "+this.zipCode;
}

	public List<Carpool> getCarpool() {
		return carpool;
	}

	public void setCarpool(List<Carpool> carpool) {
		this.carpool = carpool;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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

	public void setStreetAddress(String streetAddress) {
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
	public void addItem (Carpool item) {carpool.add(item);}

//	@Override
//	public String toString() {
//		return "Employee [employeeId=" + employeeId + ", name=" + name + ", username=" + username + ", password="
//				+ password + ", streetAddress=" + streetAddress + ", city=" + city + ", zipCode=" + zipCode
//				+ ", company=" + company + ", carpool=" + carpool + "]";
//	}





}

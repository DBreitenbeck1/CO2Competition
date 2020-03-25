package co.grandcircus.CO2Competition.Objects;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity

public class Employee implements Serializable {

	// Serializable attribute - Allows user to stay logged in when server refreshes -- Sam
	private static final long serialVersionUID = 1L;
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
	private Integer score =0; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Company company;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Carpool> carpool;
	
	//Boolean for easily checking if the employee is the company admin or not
	public Boolean isAdmin() {
		return getCompany().getAdmin().getUsername().equals(getUsername());
	}
	
	public void addToScore(int add) {
		this.score +=add;
	}
	

	public Integer getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
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

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", username=" + username + ", password="
				+ password + ", streetAddress=" + streetAddress + ", city=" + city + ", zipCode=" + zipCode
				+ ", company=" + company + ", carpool=" + carpool + "]";
	}





}

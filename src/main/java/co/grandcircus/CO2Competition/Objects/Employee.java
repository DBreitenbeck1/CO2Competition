//package co.grandcircus.CO2Competition.Objects;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//
//@Entity
//
//public class Employee {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//
//	private Long employeeId;
//	private String name;
//	private String streetAdress;
//	private String username;
//	private String password;
//	
//	@Override
//	public String toString() {
//		return "Employee [employeeId=" + employeeId + ", name=" + name + ", streetAdress=" + streetAdress
//				+ ", username=" + username + ", password=" + password + ", city=" + city + ", zipCode=" + zipCode
//				+ ", companyId=" + companyId + "]";
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public Long getEmployeeId() {
//		return employeeId;
//	}
//
//	public void setEmployeeId(Long employeeId) {
//		this.employeeId = employeeId;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getStreetAdress() {
//		return streetAdress;
//	}
//
//	public void setStreetAdress(String streetAdress) {
//		this.streetAdress = streetAdress;
//	}
//
//	public String getCity() {
//		return city;
//	}
//
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//	public String getZipCode() {
//		return zipCode;
//	}
//
//	public void setZipCode(String zipCode) {
//		this.zipCode = zipCode;
//	}
//
//	public Long getCompanyId() {
//		return companyId;
//	}
//
//	public void setCompanyId(Long companyId) {
//		this.companyId = companyId;
//	}
//
//	private String city;
//	private String zipCode;
//	@ManyToOne
//	private Long companyId;
//
//}

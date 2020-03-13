package co.grandcircus.CO2Competition.Objects;
//package co.grandcircus.CO2Competition.objects;
//
//import java.util.Set;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//
//@Entity
//
//public class Carpool {
//
//	// no argument constructor
//
//	public Carpool() {
//
//	}
//
//	// to string
//
//	@Override
//	public String toString() {
//		return "Carpool [id=" + id + ", driver=" + driver + ", origMi=" + origMi + ", origGas=" + origGas
//				+ ", savedGas=" + savedGas + ", passengers=" + passengers + "]";
//	}
//
//	// getters and setters below
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Employee getDriver() {
//		return driver;
//	}
//
//	public void setDriver(Employee driver) {
//		this.driver = driver;
//	}
//
//	public Double getOrigMi() {
//		return origMi;
//	}
//
//	public void setOrigMi(Double origMi) {
//		this.origMi = origMi;
//	}
//
//	public Double getOrigGas() {
//		return origGas;
//	}
//
//	public void setOrigGas(Double origGas) {
//		this.origGas = origGas;
//	}
//
//	public Double getSavedGas() {
//		return savedGas;
//	}
//
//	public void setSavedGas(Double savedGas) {
//		this.savedGas = savedGas;
//	}
//
//	public Set<Employee> getPassengers() {
//		return passengers;
//	}
//
//	public void setPassengers(Set<Employee> passengers) {
//		this.passengers = passengers;
//	}
//
//	// fields
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//
//	private Long id;
//	@OneToOne(mappedBy = "employee")
//	private Employee driver;
//	private Double origMi;
//	private Double origGas;
//	private Double savedGas;
//	@OneToMany(mappedBy = "employee")
//	private Set<Employee> passengers;
//
//}

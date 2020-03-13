package co.grandcircus.CO2Competition.Objects;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="carpool")
public class Carpool {

	// no argument constructor

	public Carpool() {

	}

	// to string

	@Override
	public String toString() {
		return "Carpool [id=" + id + ", origMi=" + origMi + ", origGas=" + origGas + ", savedGas=" + savedGas
				+ ", employees=" + employees + "]";
	}

	// getters and setters below

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Employee getDriver() {
//		return driver;
//	}
//
//	public void setDriver(Employee driver) {
//		this.driver = driver;
//	}

	public Double getOrigMi() {
		return origMi;
	}

	public void setOrigMi(Double origMi) {
		this.origMi = origMi;
	}

	public Double getOrigGas() {
		return origGas;
	}

	public void setOrigGas(Double origGas) {
		this.origGas = origGas;
	}

	public Double getSavedGas() {
		return savedGas;
	}

	public void setSavedGas(Double savedGas) {
		this.savedGas = savedGas;
	}

	

	// fields

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="id")
	private Long id;
//	@OneToOne(mappedBy = "employee")
//	private Employee driver;
	@Column(name="original_mileage")
	private Double origMi;
	@Column(name="original_gas")
	private Double origGas;
	@Column(name="saved_gas")
	private Double savedGas;
	@OneToMany(mappedBy = "carpool")
	private Set<Employee> employees;

}

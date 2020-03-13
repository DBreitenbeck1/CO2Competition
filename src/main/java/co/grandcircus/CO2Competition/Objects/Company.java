package co.grandcircus.CO2Competition.Objects;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity

public class Company {

	// no argument constructor

	public Company() {

	}

	// to string

	@Override
	public String toString() {
		return "Company [id=" + id + ", totEm=" + totEm + ", totSav=" + totSav + ", employees=" + employees + "]";
	}

	// getters and setters below

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotEm() {
		return totEm;
	}

	public void setTotEm(Double totEm) {
		this.totEm = totEm;
	}

	public Double getTotSav() {
		return totSav;
	}

	public void setTotSav(Double totSav) {
		this.totSav = totSav;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	// fields

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private Double totEm;
	private Double totSav;
	@OneToMany(mappedBy = "primaryJob")
	private Set<Employee> employees;

}

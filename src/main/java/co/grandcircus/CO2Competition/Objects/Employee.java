package co.grandcircus.CO2Competition.Objects;
//package co.grandcircus.CO2Competition.objects;
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
//	// no argument constructor
//
//	public Employee() {
//
//	}
//
//	// to string
//
//	@Override
//	public String toString() {
//		return "Employee [id=" + id + ", adress=" + adress + ", totEm=" + totEm + ", totSav=" + totSav + ", mpg=" + mpg
//				+ ", primaryJob=" + primaryJob + ", secondaryJob=" + secondaryJob + "]";
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
//	public String getAdress() {
//		return adress;
//	}
//
//	public void setAdress(String adress) {
//		this.adress = adress;
//	}
//
//	public Double getTotEm() {
//		return totEm;
//	}
//
//	public void setTotEm(Double totEm) {
//		this.totEm = totEm;
//	}
//
//	public Double getTotSav() {
//		return totSav;
//	}
//
//	public void setTotSav(Double totSav) {
//		this.totSav = totSav;
//	}
//
//	public Double getMpg() {
//		return mpg;
//	}
//
//	public void setMpg(Double mpg) {
//		this.mpg = mpg;
//	}
//
//	public Company getPrimaryJob() {
//		return primaryJob;
//	}
//
//	public void setPrimaryJob(Company primaryJob) {
//		this.primaryJob = primaryJob;
//	}
//
//	public Company getSecondaryJob() {
//		return secondaryJob;
//	}
//
//	public void setSecondaryJob(Company secondaryJob) {
//		this.secondaryJob = secondaryJob;
//	}
//
//	// fields
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//
//	private Long id;
//	private String adress;
//	private Double totEm;
//	private Double totSav;
//	private Double mpg;
//	@ManyToOne
//	private Company primaryJob;
//	@ManyToOne
//	private Company secondaryJob;
//
//}

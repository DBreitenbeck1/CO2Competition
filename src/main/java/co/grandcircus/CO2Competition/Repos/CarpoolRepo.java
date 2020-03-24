package co.grandcircus.CO2Competition.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.CO2Competition.Objects.Carpool;
import co.grandcircus.CO2Competition.Objects.Company;
import co.grandcircus.CO2Competition.Objects.Employee;


public interface CarpoolRepo extends JpaRepository<Carpool, Long> {

	List<Carpool> findByCompanyOrderByDateDesc(Company company);
	
	List<Carpool> findByCompanyAndDateAfterOrderByDateDesc(Company company, String date);
	
	List<Carpool> findByEmployeesContaining(Employee employee);
	
	List<Carpool> findByEmployeesContainingAndDateAfterOrderByDateDesc(Employee employee, String date);

	List<Carpool> findByCompanyAndDateBetweenOrderByDateDesc(Company company, String date1, String date2);

	List<Carpool> findByEmployeesContainingAndDateBetweenOrderByDateDesc(Employee employee, String date1, String date2);

	

}

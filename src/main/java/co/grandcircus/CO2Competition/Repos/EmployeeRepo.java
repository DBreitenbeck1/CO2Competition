package co.grandcircus.CO2Competition.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.grandcircus.CO2Competition.Objects.Employee;
import co.grandcircus.CO2Competition.Objects.Score;


public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	Employee findByUsernameIgnoreCase(String username);
	List<Employee> findByCity (String city);
	
	Employee findByCompany(String companyname);
	
	
	
	@Query(value = "SELECT round(sum(co2),2) AS 'CO2', employees_employee_id FROM carpool INNER JOIN (" + 
			"SELECT * FROM employee_carpool WHERE employees_employee_id IN (" + 
			"SELECT employee_id FROM employee WHERE company_company_id = :company_id" + 
			")) AS A" + 
			"ON (carpool_id = A.carpool_carpool_id)" + 
			"GROUP BY employees_employee_id" + 
			"ORDER BY CO2 DESC;",
			nativeQuery = true
			)
	public List<Score> findScoresByCompany(@Param("company_id") Long company_id);
}

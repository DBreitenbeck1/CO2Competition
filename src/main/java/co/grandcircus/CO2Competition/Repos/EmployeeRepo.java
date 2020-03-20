package co.grandcircus.CO2Competition.Repos;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.grandcircus.CO2Competition.Objects.Employee;
import co.grandcircus.CO2Competition.Objects.Score;


public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	Employee findByUsernameIgnoreCase(String username);
	List<Employee> findByCity (String city);
	Employee findByCompany(String companyname);
	
	@Query(value="SELECT vehicleType FROM Employee")
	Set<String> findAllVehicleType();
	
	
	@Query(value = "SELECT round(sum(co2),2) AS score, name AS employee FROM carpool INNER JOIN ( " + 
			"SELECT * FROM employee_carpool INNER JOIN (" + 
			"SELECT employee.employee_id, name FROM employee WHERE company_company_id = :company_id " + 
			") as B ON (employee_carpool.employees_employee_id = B.employee_id) " + 
			") AS A " + 
			"ON (carpool_id = A.carpool_carpool_id) " + 
			"GROUP BY employees_employee_id " + 
			"ORDER BY score DESC;",
			nativeQuery = true
			)
	public List<Score> findScoresByCompany(@Param("company_id") Long company_id);
	
	@Query(value = "SELECT round(sum(co2),2) AS score, name AS employee FROM carpool INNER JOIN ( " + 
			"SELECT * FROM employee_carpool INNER JOIN (" + 
			"SELECT employee.employee_id, name FROM employee WHERE employee_id = :employee_id " + 
			") as B ON (employee_carpool.employees_employee_id = B.employee_id) " + 
			") AS A " + 
			"ON (carpool_id = A.carpool_carpool_id) " + 
			"GROUP BY employees_employee_id " + 
			"ORDER BY score DESC;",
			nativeQuery = true
			)
	public Score findScoreByEmployee(@Param("employee_id") Long employee_id);
	
	
	
}

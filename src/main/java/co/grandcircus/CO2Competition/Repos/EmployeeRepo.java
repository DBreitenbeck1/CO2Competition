package co.grandcircus.CO2Competition.Repos;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import co.grandcircus.CO2Competition.Objects.Carpool;
import co.grandcircus.CO2Competition.Objects.Company;
import co.grandcircus.CO2Competition.Objects.Employee;
import co.grandcircus.CO2Competition.Objects.Score;


public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	Employee findByUsernameIgnoreCase(String username);
	List<Employee> findByCity (String city);
	Employee findByCompany(String companyname);
	
	List<Employee> findByCompanyName(String name);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE `CO2_competition`.`employee` SET `city` = :city, `name` = :name,"
			+ " `password` = :password, `street_address` = :streetAddress, `username` = :username,"
			+ " `zip_code` = :zipCode, `company_company_id` = :companyId, `vehicle_type` = :vehicleType "
			+ "WHERE (`employee_id` = :employeeId);",
			nativeQuery = true)
	void update(
			@Param("city") String city,
			@Param("name") String name,
			@Param("password") String password,
			@Param("streetAddress") String streetAddress,
			@Param("username") String username,
			@Param("zipCode") String zipCode,
			@Param("companyId") Long companyId,
			@Param("vehicleType") String vehicleType,
			@Param("employeeId") Long employeeId
			);

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
	
	List<Employee> findByCompany(Company company);
	
	List<Employee> findByCompanyOrderByScoreDesc(Company company);
	
	//List<Employee> findByCompanyOrderByScoreDesc(Company company);
	
	@Query(value="SELECT sum(total_score) AS score FROM employee WHERE company_company_id = :company_id", nativeQuery = true)
	public List<Score> findScoreByTotalScore(@Param("company_id") Long company_id);
	List<Employee> findByCarpoolContains(Carpool carpool);
	
}

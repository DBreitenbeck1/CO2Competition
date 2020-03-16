package co.grandcircus.CO2Competition.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.CO2Competition.Objects.Employee;


public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	Employee findByUsernameIgnoreCase(String username);
}

package co.grandcircus.CO2Competition.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.CO2Competition.Objects.Company;


public interface CompanyRepo extends JpaRepository<Company, Long> {

	Company findByName(String name);
}

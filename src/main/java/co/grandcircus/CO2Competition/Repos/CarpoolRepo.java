package co.grandcircus.CO2Competition.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.CO2Competition.Objects.Carpool;


public interface CarpoolRepo extends JpaRepository<Carpool, Long> {
	

}

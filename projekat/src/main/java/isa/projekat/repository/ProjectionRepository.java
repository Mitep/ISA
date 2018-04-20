package isa.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.Projection;
import isa.projekat.model.Ticket;

public interface ProjectionRepository extends CrudRepository<Projection, Long> {

	Projection getProjectionById(Long id);
	
	@Query("select p from Projection p where p.theatreCinema.tcId = ?1")
	List<Projection> getAllProjectionOfTheatreCinema(Long thecinId);
	
}

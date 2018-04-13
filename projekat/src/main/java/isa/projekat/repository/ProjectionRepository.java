package isa.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.Projection;

public interface ProjectionRepository extends CrudRepository<Projection, Long> {

	@Query("select p from Projection p where p.theatreCinema.id = ?1")
	List<Projection> getAllProjectionOfTheatreCinema(Long projId);
	
}

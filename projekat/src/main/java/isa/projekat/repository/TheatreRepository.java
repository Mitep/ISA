package isa.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import isa.projekat.model.TheatreCinema;

public interface TheatreRepository extends CrudRepository<TheatreCinema, Long> {
		
	TheatreCinema findByName(String name);
	List<TheatreCinema> findAll();
	
	@Query("select c from TheatreCinema c where c.type = 0")
	List<TheatreCinema> findAllTheatres();

	@Transactional
    @Modifying
    @Query("update TheatreCinema c "
    		+ "set c.name = ?1, c.description = ?2 "
    		+ "where c.id = ?3")
	void updateTheatre(String name, String description, Long id);
}

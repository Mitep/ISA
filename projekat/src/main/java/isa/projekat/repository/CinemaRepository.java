package isa.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import isa.projekat.model.TheatreCinema;

public interface CinemaRepository extends CrudRepository<TheatreCinema, Long> {
	
	TheatreCinema findByName(String name);
	
	List<TheatreCinema> findAll();
	
	TheatreCinema findByTcId(Long tcId);
	
	@Query("select c from TheatreCinema c where c.type = 1")
	List<TheatreCinema> findAllCinemas();
	
	@Transactional
    @Modifying
    @Query("update TheatreCinema c "
    		+ "set c.name = ?1, c.description = ?2 "
    		+ "where c.id = ?3")
	void updateCinema(String name, String description, Long id);
	
}

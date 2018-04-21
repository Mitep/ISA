package isa.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import isa.projekat.model.TheatreCinema;
import isa.projekat.model.User;

public interface TheatreRepository extends CrudRepository<TheatreCinema, Long> {
		
	TheatreCinema findByName(String name);
	List<TheatreCinema> findAll();
	
	TheatreCinema findByTcId(Long tcId);
	
	@Query("select c from TheatreCinema c where c.type = 0")
	List<TheatreCinema> findAllTheatres();
	
	//@Query("select c from TheatreCinema c where ?1 in c.adminiBioPoz and where c.type = 1")
	List<TheatreCinema> findByAdminiBioPoz(User admin);

	@Transactional
    @Modifying
    @Query("update TheatreCinema c "
    		+ "set c.name = ?1, c.description = ?2 "
    		+ "where c.id = ?3")
	void updateTheatre(String name, String description, Long id);
}

package isa.projekat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.TheatreCinema;

public interface TheatreRepository extends CrudRepository<TheatreCinema, Integer> {
		
	TheatreCinema findByName(String name);
	List<TheatreCinema> findAll();

}

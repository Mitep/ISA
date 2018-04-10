package isa.projekat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.Oglas;

public interface OglasRepository extends CrudRepository<Oglas, Integer>{
	
	Oglas findByNazivOglasa(String nazivOglasa);
	List<Oglas> findAll();
	Oglas findByOglasId(Long oglasId);
}

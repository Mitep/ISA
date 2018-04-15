package isa.projekat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.RequestOglasa;


public interface RequestOglasaRepository extends CrudRepository<RequestOglasa, Integer> {

	RequestOglasa findByRoId(Long roId);
	List<RequestOglasa> findAll();
}

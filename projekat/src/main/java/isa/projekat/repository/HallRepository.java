package isa.projekat.repository;

import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.Hall;

public interface HallRepository extends CrudRepository<Hall, Long>{

	Hall getHallById(Long id);
}

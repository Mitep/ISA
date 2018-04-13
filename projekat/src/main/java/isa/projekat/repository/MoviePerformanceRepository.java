package isa.projekat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.MoviePerformance;

public interface MoviePerformanceRepository extends CrudRepository<MoviePerformance, Long> {

	List<MoviePerformance> findAll();
	MoviePerformance findByMovieId(Long id);
}

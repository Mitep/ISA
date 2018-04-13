package isa.projekat.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.MoviePerformance;

public interface MoviePerformanceRatingRepository extends CrudRepository<MoviePerformance, Long> {

	@Query("select avg(m.rating) from MoviePerformanceRating m where m.moviePerformance.id = ?1")
	String getAvgMovieRating(Long id);
	
	@Query("select avg(m.rating) from MoviePerformanceRating m where m.moviePerformance.id = ?1")
	String getAvgPerformanceRating(Long id);
	
}

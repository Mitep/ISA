package isa.projekat.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.AmbientRating;
import isa.projekat.model.TheatreCinema;

public interface AmbientRatingRepository extends CrudRepository<AmbientRating, Long> {
	
	@Query("select avg(r.rating) from AmbientRating r where r.theatreCinema = ?1")
	String getAvgRating(TheatreCinema theatreCinema);

}
 
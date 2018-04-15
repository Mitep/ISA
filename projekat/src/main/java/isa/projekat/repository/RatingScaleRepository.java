package isa.projekat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.RatingScale;

public interface RatingScaleRepository extends CrudRepository<RatingScale, Integer> {

	List<RatingScale> findAll();
	RatingScale findByMedaljeId(Integer medaljeId);
}

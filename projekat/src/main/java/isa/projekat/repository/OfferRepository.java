package isa.projekat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.Offer;

public interface OfferRepository extends CrudRepository<Offer, Long> {
	List<Offer> findAll();
	Offer findByOfferId(Long offerId);

}
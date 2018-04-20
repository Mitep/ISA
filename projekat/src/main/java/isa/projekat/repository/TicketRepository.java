package isa.projekat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

	Ticket getById(Long id);
	
}

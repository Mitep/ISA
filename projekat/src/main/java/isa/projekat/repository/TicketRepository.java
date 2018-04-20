package isa.projekat.repository;

import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.Seat;
import isa.projekat.model.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long>{

	Ticket findBySeat(Seat seat);
	Ticket getById(Long id);

	
}

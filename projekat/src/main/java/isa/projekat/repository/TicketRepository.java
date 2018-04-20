package isa.projekat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.Projection;
import isa.projekat.model.Seat;
import isa.projekat.model.Ticket;
import isa.projekat.model.User;

public interface TicketRepository extends CrudRepository<Ticket, Long>{

	Ticket findBySeat(Seat seat);
	Ticket getById(Long id);
	Ticket findBySeatAndProjection(Seat seat,Projection pr);
	List<Ticket> findByUser(User us);

	
}

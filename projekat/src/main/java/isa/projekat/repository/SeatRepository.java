package isa.projekat.repository;

import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.Seat;

public interface SeatRepository  extends CrudRepository<Seat, Long>{

	Seat findSeatById(Long id);
	
	Seat findByRowNumAndColNum(int rowNum,int colNum);
}

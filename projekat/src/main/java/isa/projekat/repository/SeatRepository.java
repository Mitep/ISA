package isa.projekat.repository;

import org.springframework.data.repository.CrudRepository;

import isa.projekat.model.Seat;

public interface SeatRepository  extends CrudRepository<Seat, Long>{

	
	Seat findByRowNumAndColNum(int rowNum,int colNum);
}

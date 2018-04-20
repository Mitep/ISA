package isa.projekat.service;

import java.util.List;
import java.util.Set;

import isa.projekat.model.Hall;
import isa.projekat.model.dtos.NewHallDTO;
import isa.projekat.model.dtos.SeatDTO;

public interface HallService {

	String getSeatState(SeatDTO seat);
	
	List<Hall> getAllHallsOfTheatreCinema(Long theCinId);
	
	void newHall(NewHallDTO newHall);
	
	void editHall(NewHallDTO editHall);
	
	void deleteHall(Long hallId);
}

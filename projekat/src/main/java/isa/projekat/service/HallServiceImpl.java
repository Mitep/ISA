package isa.projekat.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.model.Hall;
import isa.projekat.model.TheatreCinema;
import isa.projekat.model.dtos.NewHallDTO;
import isa.projekat.model.dtos.SeatDTO;
import isa.projekat.repository.CinemaRepository;
import isa.projekat.repository.HallRepository;

@Service
public class HallServiceImpl implements HallService {

	@Autowired
	private HallRepository hallRepository;
	
	@Autowired
	private CinemaRepository cinemaRepository;
	
	@Override
	public String getSeatState(SeatDTO seat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hall> getAllHallsOfTheatreCinema(Long theCinId) {
		// TODO Auto-generated method stub
		TheatreCinema tc = cinemaRepository.findByTcId(theCinId);
		return tc.getHalls();
	}

	@Override
	public void newHall(NewHallDTO newHall) {
		// TODO Auto-generated method stub
		Hall h = new Hall();
		h.setTheatreCinema(cinemaRepository.findByTcId(newHall.getTheatreCinemaId()));
		h.setName(newHall.getHallName());
		hallRepository.save(h);
	}

	@Override
	public void editHall(NewHallDTO editHall) {
		// TODO Auto-generated method stub
		Hall h = hallRepository.getHallById(editHall.getTheatreCinemaId());
		
		if(!editHall.getHallName().equals(""))
			h.setName(editHall.getHallName());
		
		hallRepository.save(h);
	}

	@Override
	public void deleteHall(Long hallId) {
		// TODO Auto-generated method stub
		Hall h = hallRepository.getHallById(hallId);
		hallRepository.delete(h);
	}

}

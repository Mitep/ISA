package isa.projekat.service;

import java.util.List;

import isa.projekat.model.dtos.CinemaDTO;
import isa.projekat.model.dtos.ProjectionDTO;

public interface CinemaService {

	List<CinemaDTO> getAllCinemas();
	
	List<ProjectionDTO> getCinemaProjections(Long cinemaId);
	
	void editBasicInfo(CinemaDTO newCinema);
}

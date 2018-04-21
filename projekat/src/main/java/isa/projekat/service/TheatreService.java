package isa.projekat.service;

import java.util.List;

import isa.projekat.model.TheatreCinema;
import isa.projekat.model.User;
import isa.projekat.model.dtos.ProjectionDTO;
import isa.projekat.model.dtos.TheatreDTO;

public interface TheatreService {

	List<TheatreDTO> getAllTheatres();
	
	List<TheatreDTO> getAllTheatresForAdmin(User user);
	
	List<ProjectionDTO> getTheatreProjections(Long theatreId);
	
	void editBasicInfo(TheatreDTO newTheatre);
	
	void editTheatre(TheatreCinema t);
	
	TheatreCinema getTheatre(Long id);
	
}

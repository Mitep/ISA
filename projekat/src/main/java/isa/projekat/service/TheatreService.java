package isa.projekat.service;

import java.util.List;

import isa.projekat.model.dtos.ProjectionDTO;
import isa.projekat.model.dtos.TheatreDTO;

public interface TheatreService {

	List<TheatreDTO> getAllTheatres();
	
	List<ProjectionDTO> getTheatreProjections(Long theatreId);
	
	void editBasicInfo(TheatreDTO newTheatre);
	
}

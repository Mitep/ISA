package isa.projekat.service;

import java.util.List;

import isa.projekat.model.TheatreCinema;
import isa.projekat.model.User;
import isa.projekat.model.dtos.CinemaDTO;
import isa.projekat.model.dtos.ProjectionDTO;
import isa.projekat.model.dtos.TicketDTO;

public interface CinemaService {

	List<CinemaDTO> getAllCinemas();
	
	List<ProjectionDTO> getCinemaProjections(Long cinemaId);
	
	void editBasicInfo(CinemaDTO newCinema);
	
	void editCinema(TheatreCinema c);
	
	TheatreCinema getCinema(Long id);
	
	List<CinemaDTO> getCinemasForAdmin(User user);
	
	List<TicketDTO> getFastTickets(Long cinId);
	
}

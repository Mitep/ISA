package isa.projekat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.model.Projection;
import isa.projekat.model.TheatreCinema;
import isa.projekat.model.TheatreCinemaEnum;
import isa.projekat.model.Ticket;
import isa.projekat.model.User;
import isa.projekat.model.dtos.CinemaDTO;
import isa.projekat.model.dtos.ProjectionDTO;
import isa.projekat.model.dtos.TicketDTO;
import isa.projekat.repository.AmbientRatingRepository;
import isa.projekat.repository.CinemaRepository;
import isa.projekat.repository.MoviePerformanceRatingRepository;
import isa.projekat.repository.ProjectionRepository;

@Service
public class CinemaServiceImpl implements CinemaService {

	@Autowired
	private CinemaRepository cinemaRepository;
	
	@Autowired
	private AmbientRatingRepository ambientRatingRepository;
	
	@Autowired
	private ProjectionRepository projectionRepository;
	
	@Autowired
	private MoviePerformanceRatingRepository movieRatingRepository;
	
	@Override
	public List<CinemaDTO> getAllCinemas() {
		// TODO Auto-generated method stub
		List<CinemaDTO> dtos = new ArrayList<CinemaDTO>();
		List<TheatreCinema> cinemas = cinemaRepository.findAllCinemas();
		for(TheatreCinema c : cinemas){
			String avgRating = ambientRatingRepository.getAvgRating(c);
			if(avgRating == null)
				avgRating = "Bez rejtinga";
			
			CinemaDTO dto = new CinemaDTO(c.getId(), c.getName(), c.getAdress(), c.getDescription(), avgRating);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<ProjectionDTO> getCinemaProjections(Long cinemaId) {
		// TODO Auto-generated method stub
		List<Projection> projections = projectionRepository.getAllProjectionOfTheatreCinema(cinemaId);
		List<ProjectionDTO> dtos = new ArrayList<ProjectionDTO>();
		for(Projection p : projections){
			//iz filma izvaditi prosecnu ocenu 
			String movieRating = movieRatingRepository.getAvgMovieRating(p.getMoviePerformance().getMovieId());
			if(movieRating == null)
				movieRating = "Bez rejtinga";	
			ProjectionDTO dto = new ProjectionDTO(p.getId(), p.getName(), p.getDescription(), p.getProjectionDateTime(),
					movieRating, p.getMoviePerformance().getMovieId(), p.getMoviePerformance().getName(), p.getHall().getId(), p.getHall().getName(),
					p.getTheatreCinema().getId(), p.getTheatreCinema().getName());
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public void editBasicInfo(CinemaDTO newCinema) {
		// TODO Auto-generated method stub
		cinemaRepository.updateCinema(newCinema.getName(), newCinema.getDescription(), newCinema.getId());
	}

	@Override
	public void editCinema(TheatreCinema c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TheatreCinema getCinema(Long id) {
		// TODO Auto-generated method stub
		return cinemaRepository.findByTcId(id);
	}

	@Override
	public List<CinemaDTO> getCinemasForAdmin(User user) {
		// TODO Auto-generated method stub
		List<TheatreCinema> cinemaList = cinemaRepository.findByAdminiBioPoz(user);
		List<CinemaDTO> cinemaRet = new ArrayList<CinemaDTO>();
		for(TheatreCinema c : cinemaList){
			String avgRating = ambientRatingRepository.getAvgRating(c);
			if(avgRating == null)
				avgRating = "Bez rejtinga";
			
			CinemaDTO cd = new CinemaDTO(c.getId(), c.getName(), c.getAdress(), c.getDescription(), avgRating);
			if(c.getType().equals(TheatreCinemaEnum.CINEMA))
				cinemaRet.add(cd);
		}
		
		return cinemaRet;
	}

	@Override
	public List<TicketDTO> getFastTickets(Long cinId) {
		// TODO Auto-generated method stub
		TheatreCinema tc = cinemaRepository.findByTcId(cinId);
		List<TicketDTO> tickets = new ArrayList<TicketDTO>();
		for(Projection p : tc.getProjekcije()){
			for(Ticket t : p.getTickets()){
				//proveamo karte
				if(t.getDiscount() > 0 && t.getUser() == null){
					//pretvaramo ticket u ticketDTO i dodajemo u listu
					TicketDTO ttemp = new TicketDTO(t.getId(), t.getPrice(), t.getDiscount(), t.getSeat().getRowNum(), t.getSeat().getColNum(), t.getProjection().getName() ,t.getSeat().getSegment().getName(), t.getProjection().getHall().getName());
					ttemp.setMovieName(t.getProjection().getMoviePerformance().getName());
					ttemp.setProjectionDate(t.getProjection().getProjectionDateTime());
					ttemp.setProjectionTime(t.getProjection().getProjectionDateTime());
					tickets.add(ttemp);
				}
			}
		}
		return tickets;
	}

}

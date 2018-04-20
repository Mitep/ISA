package isa.projekat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.model.Hall;
import isa.projekat.model.Projection;
import isa.projekat.model.Seat;
import isa.projekat.model.Segment;
import isa.projekat.model.Ticket;
import isa.projekat.model.dtos.ProjectionDTO;
import isa.projekat.repository.HallRepository;
import isa.projekat.repository.MoviePerformanceRatingRepository;
import isa.projekat.repository.MoviePerformanceRepository;
import isa.projekat.repository.ProjectionRepository;
import isa.projekat.repository.TicketRepository;
import isa.projekat.repository.UserRepository;

@Service
public class ProjectionServiceImpl implements ProjectionService {

	@Autowired
	private ProjectionRepository projectionRepository;
	
	@Autowired
	private HallRepository hallRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MoviePerformanceRatingRepository moviePerformanceRatingRepository;
	
	@Autowired
	private MoviePerformanceRepository moviePerformanceRepository;

	@Override
	public List<ProjectionDTO> getProjections(Long cintheId) {
		// TODO Auto-generated method stub
		
		List<Projection> projs = projectionRepository.getAllProjectionOfTheatreCinema(cintheId);
		List<ProjectionDTO> retProj = new ArrayList<ProjectionDTO>();
		
		for(Projection p : projs) {
			String avgRating = moviePerformanceRatingRepository.getAvgMovieRating(p.getMoviePerformance().getMovieId());
			if(avgRating == null)
				avgRating = "Bez rejtinga";
			ProjectionDTO pr = new ProjectionDTO(p.getId(), p.getName(), p.getDescription(), p.getProjectionDateTime(), avgRating,
					p.getMoviePerformance().getMovieId(), p.getMoviePerformance().getName(), p.getHall().getId(), p.getHall().getName(), p.getTheatreCinema().getId(),
					p.getTheatreCinema().getName());
			retProj.add(pr);
		}
		
		return retProj;
	}

	@Override
	public void createNewProjection(ProjectionDTO newProj) {
		// TODO Auto-generated method stub
		Projection p = new Projection();
		p.setName(newProj.getName());
		Hall h = hallRepository.getHallById(newProj.getHallId());
		p.setHall(h);
		p.setTheatreCinema(h.getTheatreCinema());
		p.setDescription(newProj.getDescription());
		p.setProjectionDateTime(newProj.getProjectionDateTime());
		p.setMoviePerformance(moviePerformanceRepository.findByMovieId(newProj.getMoviePerformanceId()));	
		projectionRepository.save(p);

		int numSeats = newProj.getDiscountSeatNumber();
		List<Ticket> tickets = new ArrayList<Ticket>();
		for(Segment seg : h.getSegments()){
			for(Seat seat: seg.getSeat()){
				Ticket t = new Ticket();
				t.setPrice(newProj.getPrice());
				if(numSeats == 0)
					t.setDiscount(0);
				else {
					t.setDiscount(newProj.getDiscount());
					numSeats--;
				}
				
				t.setProjection(p);
				t.setSeat(seat);
				//t.setUser(null);
				ticketRepository.save(t);
				tickets.add(t);
			}
		}
		
		p.setTickets(tickets);
		projectionRepository.save(p);

	}

	@Override
	public void updateProjection(ProjectionDTO editProj) {
		// TODO Auto-generated method stub
		
		Projection p = projectionRepository.getProjectionById(editProj.getId());
		
		p.setName(editProj.getName());				

		p.setDescription(editProj.getDescription());
		
		p.setProjectionDateTime(editProj.getProjectionDateTime());
		
		p.setMoviePerformance(moviePerformanceRepository.findByMovieId(editProj.getMoviePerformanceId()));
		
		projectionRepository.save(p);
	}

	@Override
	public ProjectionDTO getProjection(Long id) {
		// TODO Auto-generated method stub
		Projection p = projectionRepository.getProjectionById(id);
		String avgRating = moviePerformanceRatingRepository.getAvgMovieRating(p.getMoviePerformance().getMovieId());
		if(avgRating == null)
			avgRating = "Bez rejtinga";
		ProjectionDTO pr = new ProjectionDTO(p.getId(), p.getName(), p.getDescription(), p.getProjectionDateTime(), avgRating,
				p.getMoviePerformance().getMovieId(), p.getMoviePerformance().getName(), p.getHall().getId(), p.getHall().getName(), p.getTheatreCinema().getId(),
				p.getTheatreCinema().getName());
		
		return pr;
	}

	@Override
	public void deleteProjection(Long projId) {
		// TODO Auto-generated method stub
		Projection p = projectionRepository.getProjectionById(projId);
		projectionRepository.delete(p);
	}

	@Override
	public void fastTicketReserve(Long ticketId, Long userId) {
		// TODO Auto-generated method stub
		Ticket t = ticketRepository.getById(ticketId);
		t.setUser(userRepository.findByUserId(userId));
		ticketRepository.save(t);
	}

}

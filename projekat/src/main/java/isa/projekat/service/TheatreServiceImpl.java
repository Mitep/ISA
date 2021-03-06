package isa.projekat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.model.TheatreCinema;
import isa.projekat.model.TheatreCinemaEnum;
import isa.projekat.model.User;
import isa.projekat.model.dtos.CinemaDTO;
import isa.projekat.model.dtos.ProjectionDTO;
import isa.projekat.model.dtos.TheatreDTO;
import isa.projekat.repository.AmbientRatingRepository;
import isa.projekat.repository.MoviePerformanceRatingRepository;
import isa.projekat.repository.ProjectionRepository;
import isa.projekat.repository.TheatreRepository;

@Service
public class TheatreServiceImpl implements TheatreService {

	@Autowired
	private TheatreRepository theatreRepostiory;
	
	@Autowired
	private AmbientRatingRepository ambientRatingRepository;
	
	@Autowired
	private ProjectionRepository projectionRepository;
	
	@Autowired
	private MoviePerformanceRatingRepository performanceRatingRepository;
	
	@Override
	public List<TheatreDTO> getAllTheatres() {
		// TODO Auto-generated method stub
		List<TheatreDTO> dtos = new ArrayList<TheatreDTO>();
		List<TheatreCinema> theatres = theatreRepostiory.findAllTheatres();
		for(TheatreCinema c : theatres){
			String avgRating = ambientRatingRepository.getAvgRating(c);
			TheatreDTO dto = new TheatreDTO(c.getId(), c.getName(), c.getAdress(), c.getDescription(), avgRating);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<ProjectionDTO> getTheatreProjections(Long theatreId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editBasicInfo(TheatreDTO newTheatre) {
		// TODO Auto-generated method stub
		theatreRepostiory.updateTheatre(newTheatre.getName(), newTheatre.getDescription(), newTheatre.getId());
	}

	@Override
	public void editTheatre(TheatreCinema t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TheatreCinema getTheatre(Long id) {
		// TODO Auto-generated method stub
		return theatreRepostiory.findByTcId(id);
	}

	@Override
	public List<TheatreDTO> getAllTheatresForAdmin(User user) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<TheatreCinema> cinemaList = theatreRepostiory.findByAdminiBioPoz(user);
		List<TheatreDTO> cinemaRet = new ArrayList<TheatreDTO>();
		for(TheatreCinema c : cinemaList){
			String avgRating = ambientRatingRepository.getAvgRating(c);
			if(avgRating == null)
				avgRating = "Bez rejtinga";
			
			TheatreDTO cd = new TheatreDTO(c.getId(), c.getName(), c.getAdress(), c.getDescription(), avgRating);
			if(c.getType().equals(TheatreCinemaEnum.CINEMA))
				cinemaRet.add(cd);
		}
		
		return cinemaRet;
	}

}

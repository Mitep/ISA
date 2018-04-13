package isa.projekat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.model.TheatreCinema;
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

}

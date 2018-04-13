package isa.projekat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.model.MoviePerformance;
import isa.projekat.model.User;
import isa.projekat.repository.MoviePerformanceRepository;

@RestController
@RequestMapping("/movie")
public class MoviePerformanceController {
	
	@Autowired
	private MoviePerformanceRepository movieRep;
	
	@RequestMapping(value = "/prikaziFilmove",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MoviePerformance> prikaziFilmove() {
		List<MoviePerformance> lala = movieRep.findAll();
		for(int i = 0 ; i < lala.size();i++)
			System.out.println("OVDJEEEE"+lala.get(i));
			return movieRep.findAll();
			
		}
	
	@RequestMapping(value = "/getFilmOglasa/{id}",			
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public MoviePerformance getFilmOglasa(@PathVariable Long id,HttpServletRequest request){
			
			return movieRep.findByMovieId(id);
		
		}

}

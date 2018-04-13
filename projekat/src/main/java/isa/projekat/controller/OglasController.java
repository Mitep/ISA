package isa.projekat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.model.MoviePerformance;
import isa.projekat.model.Oglas;
import isa.projekat.model.User;
import isa.projekat.repository.MoviePerformanceRepository;
import isa.projekat.repository.OglasRepository;

@RestController
@RequestMapping("/oglas")
public class OglasController {
	
	@Autowired
	private OglasRepository oglasRep;
	
	@Autowired
	private MoviePerformanceRepository movieRep;
	
	@RequestMapping(value = "/addOglas/{movieId}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean addOglas(@RequestBody Oglas oglas, @PathVariable Long movieId, HttpServletRequest request) {
			User us = (User) request.getSession().getAttribute("user");
			Oglas og = null;
			og = new Oglas(oglas.getNazivOglasa(), oglas.getOpisOglasa(), oglas.getImageOglasa(), oglas.getDatumOglasa());
			MoviePerformance movie = movieRep.findByMovieId(movieId);
			movie.getOglas().add(og);
			og.getMoviePer().add(movie);
			oglasRep.save(og);
			movieRep.save(movie);
			return true;
	}
	
	@RequestMapping(value = "/prikaziOglas",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Oglas> prikaziOglas() {
			return oglasRep.findAll();
	}

	@RequestMapping(value = "/deleteOglas/{oglasId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteOglas(@PathVariable Long oglasId) {
			Oglas o = oglasRep.findByOglasId(oglasId);
			oglasRep.delete(o);
			return true;
	}
	
	@RequestMapping(value="/izmijeniOglas/{oglasId}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean izmijeniOglas(@RequestBody Oglas oglas, @PathVariable Long oglasId) {
		
		Oglas og = oglasRep.findByOglasId(oglasId);
		og.setNazivOglasa(oglas.getNazivOglasa());
		og.setOpisOglasa(oglas.getOpisOglasa());
		og.setDatumOglasa(oglas.getDatumOglasa());
		og.setImageOglasa(oglas.getImageOglasa());
	
		oglasRep.save(og);
	
			return true;

}
	
}

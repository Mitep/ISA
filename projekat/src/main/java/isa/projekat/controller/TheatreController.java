package isa.projekat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.model.TheatreCinema;
import isa.projekat.model.TheatreCinemaEnum;
import isa.projekat.model.User;
import isa.projekat.repository.TheatreRepository;

@RestController
@RequestMapping("/pozoriste")
public class TheatreController {

	@Autowired
	private TheatreRepository theatreRep;
	
	@RequestMapping(value = "/addPozoriste",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean addPozorista(@RequestBody TheatreCinema theatre, HttpServletRequest request) {
			User us = (User) request.getSession().getAttribute("user");
			TheatreCinema the = null;
			the = new TheatreCinema(theatre.getName(), theatre.getAdress(), theatre.getDescription());
			the.setType(TheatreCinemaEnum.THEATRE);
			theatreRep.save(the);
			return true;
	}
	
	@RequestMapping(value = "/prikaziPozoriste",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TheatreCinema> prikaziPozoriste() {
			return theatreRep.findAll();
	}
}


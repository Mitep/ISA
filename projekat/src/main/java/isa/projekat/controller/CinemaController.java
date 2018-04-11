package isa.projekat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.model.Oglas;
import isa.projekat.model.TheatreCinema;
import isa.projekat.model.TheatreCinemaEnum;
import isa.projekat.model.User;
import isa.projekat.repository.CinemaRepository;

@RestController
@RequestMapping("/bioskop")
public class CinemaController {
	
	@Autowired
	private CinemaRepository cinemaRep;
	
	@RequestMapping(value = "/addBioskop",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean addBioskop(@RequestBody TheatreCinema cinema, HttpServletRequest request) {
			User us = (User) request.getSession().getAttribute("user");
			TheatreCinema cin = null;
			cin = new TheatreCinema(cinema.getName(), cinema.getAdress(), cinema.getDescription());
			cin.setType(TheatreCinemaEnum.CINEMA);
			cinemaRep.save(cin);
			return true;
	}
	
	@RequestMapping(value = "/prikaziBioskop",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TheatreCinema> prikaziBioskop() {
			return cinemaRep.findAll();
	}
	
}

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

import isa.projekat.model.Oglas;
import isa.projekat.model.TheatreCinema;
import isa.projekat.model.TheatreCinemaEnum;
import isa.projekat.model.User;

import isa.projekat.model.dtos.CinemaDTO;
import isa.projekat.model.dtos.ProjectionDTO;
import isa.projekat.model.UserRole;

import isa.projekat.repository.CinemaRepository;
import isa.projekat.service.CinemaService;

@RestController
@RequestMapping("/bioskop")
public class CinemaController {
	
	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private CinemaRepository cinemaRep;
	
	@RequestMapping(value = "/addBioskop",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean addBioskop(@RequestBody TheatreCinema cinema, HttpServletRequest request) {
			User us = (User) request.getSession().getAttribute("user");
			if(us.getUserRole().equals(UserRole.SYSADMIN)) {
			TheatreCinema cin = null;
			cin = new TheatreCinema(cinema.getName(), cinema.getAdress(), cinema.getDescription());
			cin.setType(TheatreCinemaEnum.CINEMA);
			cinemaRep.save(cin);
			return true;
			} else {
				return false;
			}
	}
	
	@RequestMapping(value = "/prikaziBioskop",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TheatreCinema> prikaziBioskop() {
			return cinemaRep.findAll();
	}
	
	@RequestMapping(
			value= {"/sviBioskopi"},
			method = {RequestMethod.GET},
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<CinemaDTO> getAllCinemas(){
		return cinemaService.getAllCinemas();
	}
	
	@RequestMapping(
			value= {"/{id}/projekcije"},
			method = {RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProjectionDTO> getAllProjections(@PathVariable("id") Long id){
		return cinemaService.getCinemaProjections(id);
	}
	
	@RequestMapping(value = "/izmeniOsnovneInformacije",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void createProjection(@RequestBody CinemaDTO newCinema){
		cinemaService.editBasicInfo(newCinema);
	}
	
}

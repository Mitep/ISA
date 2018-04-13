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

import isa.projekat.model.TheatreCinema;
import isa.projekat.model.TheatreCinemaEnum;
import isa.projekat.model.User;

import isa.projekat.model.dtos.CinemaDTO;
import isa.projekat.model.dtos.ProjectionDTO;
import isa.projekat.model.dtos.TheatreDTO;
import isa.projekat.repository.TheatreRepository;
import isa.projekat.service.TheatreService;

import isa.projekat.model.UserRole;
import isa.projekat.repository.UserRepository;

@RestController
@RequestMapping("/pozoriste")
public class TheatreController {

	@Autowired
	private TheatreService theatreService;
	
	@Autowired
	private TheatreRepository theatreRep;
	
	@Autowired
	private UserRepository userRep;
	
	
	@RequestMapping(value = "/addPozoriste/{userId}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean addPozorista(@RequestBody TheatreCinema theatre,@PathVariable Long userId, HttpServletRequest request) {
			User us = (User) request.getSession().getAttribute("user");
			if(us.getUserRole().equals(UserRole.SYSADMIN)) {
			TheatreCinema the = null;
			the = new TheatreCinema(theatre.getName(), theatre.getAdress(), theatre.getDescription());
			the.setType(TheatreCinemaEnum.THEATRE);
			User admin = userRep.findByUserId(userId);
			admin.getBioPozAdmini().add(the);
			the.getAdminiBioPoz().add(admin);
			System.out.println("dodjes li ddosaodsa");
			for(int i = 0; i < admin.getBioPozAdmini().size();i++) {
				System.out.println("--------" + admin.getBioPozAdmini().get(i).getName());
				
			}
			userRep.save(admin);
			theatreRep.save(the);
			
			
			
			return true;
			} else {
				return false;
			}
			
	}
	
	@RequestMapping(value = "/prikaziPozoriste",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TheatreCinema> prikaziPozoriste(HttpServletRequest request) {
		User us = (User)request.getSession().getAttribute("user");
		if(us.getUserRole().equals(UserRole.SYSADMIN)) {
			return theatreRep.findAll();
		}else {
			return null;
			}
			
		}
		
	@RequestMapping(
			value= {"/svaPozorista"},
			method = {RequestMethod.GET},
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<TheatreDTO> getAllTheatres(){
		return theatreService.getAllTheatres();
	}
	
	@RequestMapping(
			value= {"/{id}/projekcije"},
			method = {RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProjectionDTO> getAllProjections(@PathVariable("id") Long id){
		return theatreService.getTheatreProjections(id);
	}
	
	@RequestMapping(value = "/izmeniOsnovneInformacije",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void createProjection(@RequestBody TheatreDTO newTheatre){
		theatreService.editBasicInfo(newTheatre);
	}
}


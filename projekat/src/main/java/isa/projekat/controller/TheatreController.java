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

import isa.projekat.model.Projection;
import isa.projekat.model.TheatreCinema;
import isa.projekat.model.TheatreCinemaEnum;
import isa.projekat.model.User;
import isa.projekat.model.UserRole;
import isa.projekat.model.dtos.ProjectionDTO;
import isa.projekat.model.dtos.TheatreDTO;
import isa.projekat.repository.TheatreRepository;
import isa.projekat.repository.UserRepository;
import isa.projekat.service.TheatreService;

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
	//	if(us.getUserRole().equals(UserRole.SYSADMIN)) {
		return theatreRep.findAll();
	//	}else {
	//		return null;
	//		}
			
		}
	
	
	@RequestMapping(value = "/prikaziPozoristeZaHome",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TheatreCinema> prikaziPozoristeZaHome() {
		
		return theatreRep.findAll();
	
			
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
	

	@RequestMapping(value = "/prikaziProjekcijuPozorista/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Projection> prikaziProjekcijuPozorista(@PathVariable Long id) {
		
			TheatreCinema tc =  theatreRep.findByTcId(id);
			if(tc.getType().equals(TheatreCinemaEnum.THEATRE)) {
				
				return tc.getProjekcije();
			}else {
				return null;
			}
		}
	
	@RequestMapping(value = "/prikaziPozoriste/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public TheatreCinema prikaziPozoriste(@PathVariable Long id,HttpServletRequest request) {
		User us = (User)request.getSession().getAttribute("user");
		User pom = userRep.findByUserId(us.getUserId());
		TheatreCinema tc = theatreRep.findByTcId(id);
		if(pom.getUserRole().equals(UserRole.USER)) {
			pom.getListaTC().add(tc);
			tc.getListaKorisnika().add(pom);
		}
		userRep.save(pom);
		theatreRep.save(tc);
		return tc;
		
	}


		@RequestMapping(value = "/deletePozoriste/{id}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public boolean deletePozoriste(@PathVariable Long id, HttpServletRequest request) {
			User us = (User)request.getSession().getAttribute("user");
			if(us.getUserRole().equals(UserRole.SYSADMIN)) {
				TheatreCinema t = theatreRep.findByTcId(id);
				theatreRep.delete(t);
				return true;
				} 
			else {
				return false;
				}
			}

		@RequestMapping(value="/izmijeniPozoriste/{id}",
				method = RequestMethod.PUT,
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public boolean izmijeniPozoriste(@RequestBody TheatreCinema theatre, @PathVariable Long id, HttpServletRequest request) {
			User us = (User)request.getSession().getAttribute("user");
			if(us.getUserRole().equals(UserRole.SYSADMIN)) {
				TheatreCinema tc = theatreRep.findByTcId(id);
				tc.setName(theatre.getName());
				tc.setAdress(theatre.getAdress());
				tc.setDescription(theatre.getDescription());
				theatreRep.save(tc);
			
					return true;

		} else {
			return false;
		}
			}
	
		}
package isa.projekat.controller;

import java.util.ArrayList;
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
import isa.projekat.model.Seat;
import isa.projekat.model.TheatreCinema;
import isa.projekat.model.TheatreCinemaEnum;
import isa.projekat.model.Ticket;
import isa.projekat.model.User;
import isa.projekat.model.UserRole;
import isa.projekat.model.dtos.ProjectionDTO;
import isa.projekat.model.dtos.TheatreDTO;
import isa.projekat.repository.ProjectionRepository;
import isa.projekat.repository.SeatRepository;
import isa.projekat.repository.TheatreRepository;
import isa.projekat.repository.TicketRepository;
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
	
	
	@Autowired 
	private TicketRepository ticketRep;
	
	@Autowired 
	private SeatRepository seatRep;
	
	@Autowired 
	private ProjectionRepository proRep;
	
	
	
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
			produces = MediaType.APPLICATION_JSON_VALUE)
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
		
		@RequestMapping(
				value= {"/{id}"},
				method = {RequestMethod.GET},
				produces = MediaType.APPLICATION_JSON_VALUE)
		public TheatreCinema getCinema(@PathVariable("id") Long id){
			return theatreService.getTheatre(id);
		}
		
		@RequestMapping(
				value= {"/{id}"},
				method = {RequestMethod.POST},
				produces = MediaType.APPLICATION_JSON_VALUE,
				consumes = MediaType.APPLICATION_JSON_VALUE)
		public void editCinema(@RequestBody TheatreCinema theatre){
			theatreService.editTheatre(theatre);
		}
	
		

		@RequestMapping(value = "/postaviZauzetaSjedistaPozoriste/{sjedistaId}/{id}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public boolean postaviZauzetaSjedista(@PathVariable String sjedistaId,@PathVariable Long id, HttpServletRequest request) {
			
			System.out.println(sjedistaId);
			String[] pero = sjedistaId.split(" ");
			String[] nesto;
			List<Seat> listaZauzetihSjedista = new ArrayList<Seat>(); 
			System.out.println(pero);
			for(int i = 0; i < pero.length;i++) {
				 if (i%2!=0) {
					 nesto = pero[i].split(",");
					 Seat se = seatRep.findByRowNumAndColNum(Integer.parseInt(nesto[0]), Integer.parseInt(nesto[1]));
					 listaZauzetihSjedista.add(se);
				 }	 
			}
			Projection pr = proRep.findByProjId(id);
			
			for(int i=0;i<listaZauzetihSjedista.size();i++) {
			pr.getZauzetaSjedista().add(listaZauzetihSjedista.get(i));
			}
			
			proRep.save(pr);
			
			return true;
			
			}
		
		
		@RequestMapping(value = "/zauzmiSjedistePozoriste/{userId}/{projId}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public boolean zauzmiSjediste(@PathVariable String userId,@PathVariable Long projId, HttpServletRequest request) {
			
			User us = (User) request.getSession().getAttribute("user");
			if(userId=="nesto") {
				userId = "";
			}
			String[] pero = userId.split(" ");
			String nesto;
			List<User> listaKorisnika = new ArrayList<User>(); 
			System.out.println(pero);
			listaKorisnika.add(us);
			Projection pr = proRep.findByProjId(projId);
			System.out.println("PROJEKAT" + pr.getId());
			List<Ticket> listaTiketa = new ArrayList<Ticket>();
			for(int i = 0; i < pr.getZauzetaSjedista().size();i++) {
				Ticket t = ticketRep.findBySeat(pr.getZauzetaSjedista().get(i));
				System.out.println("TIKET" + t.getId());
				listaTiketa.add(t);
			}
			
			for(int i = 0; i < pero.length;i++) {
				 if (i%2!=0) {
					 nesto = pero[i];
					 User se = userRep.findByUserId(Long.parseLong(nesto));
					 listaKorisnika.add(se);
				 }	 
			}
			
			for(int i=0;i<listaTiketa.size();i++) {
				System.out.println(listaTiketa.get(i).getId());
			}
		
			for(int i = 0; i < listaKorisnika.size();i++) {
				System.out.println(listaKorisnika.get(i));
			}
			
			
			for(int i = 0; i < listaKorisnika.size();i++) {
				System.out.println(listaTiketa.get(i).getId());
				System.out.println(listaKorisnika.get(i).getUserId());
				listaTiketa.get(i).setUser(listaKorisnika.get(i));
				System.out.println(listaTiketa.get(i).getId());
				ticketRep.save(listaTiketa.get(i));
			}
			
			proRep.save(pr);
		
			
			return true;
			
			}
		
		
}
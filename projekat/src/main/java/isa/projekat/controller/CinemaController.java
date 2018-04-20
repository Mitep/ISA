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
import isa.projekat.model.dtos.CinemaDTO;
import isa.projekat.model.dtos.ProjectionDTO;
import isa.projekat.model.dtos.TicketDTO;
import isa.projekat.repository.CinemaRepository;
import isa.projekat.repository.ProjectionRepository;
import isa.projekat.repository.SeatRepository;
import isa.projekat.repository.TicketRepository;
import isa.projekat.repository.UserRepository;
import isa.projekat.service.CinemaService;

@RestController
@RequestMapping("/bioskop")
public class CinemaController {
	
	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private CinemaRepository cinemaRep;
	
	@Autowired 
	private UserRepository userRep;
	
	@Autowired 
	private TicketRepository ticketRep;
	
	@Autowired 
	private SeatRepository seatRep;
	
	@Autowired 
	private ProjectionRepository proRep;
	
	
	
	
	@RequestMapping(value = "/addBioskop/{userId}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean addBioskop(@RequestBody TheatreCinema cinema,@PathVariable Long userId, HttpServletRequest request) {
			User us = (User) request.getSession().getAttribute("user");
			if(us.getUserRole().equals(UserRole.SYSADMIN)) {
			TheatreCinema cin = null;
			cin = new TheatreCinema(cinema.getName(), cinema.getAdress(), cinema.getDescription());
			cin.setType(TheatreCinemaEnum.CINEMA);
			User admin = userRep.findByUserId(userId);
			admin.getBioPozAdmini().add(cin);
			cin.getAdminiBioPoz().add(admin);
			userRep.save(admin);
			cinemaRep.save(cin);
			return true;
			} else {
				return false;
			}
	}
	
	@RequestMapping(value = "/prikaziBioskop",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TheatreCinema> prikaziBioskop(HttpServletRequest request) {
		User us = (User)request.getSession().getAttribute("user");
	//	if(us.getUserRole().equals(UserRole.SYSADMIN)) {
			return cinemaRep.findAll();
	//	}else {
	//		return null;
		//	}
			
		}
		
	
	@RequestMapping(value = "/prikaziBioskopZaHome",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TheatreCinema> prikaziBioskopZaHome() {
		
		return cinemaRep.findAll();
			
		}
	
	
	@RequestMapping(
			value= {"/sviBioskopi"},
			method = {RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CinemaDTO> getAllCinemas(HttpServletRequest request){

//		User us = null;
//		if(request.getSession().getAttribute("user") != null)
		User us = (User) request.getSession().getAttribute("user");
		
		if (us.getUserRole().equals(UserRole.ADMIN))
			return cinemaService.getCinemasForAdmin(us);
		else
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
	

	@RequestMapping(value = "/prikaziProjekcijuBioskopa/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Projection> prikaziProjekcijuBioskopa(@PathVariable Long id) {
		
			TheatreCinema tc =  cinemaRep.findByTcId(id);
			if(tc.getType().equals(TheatreCinemaEnum.CINEMA)) {
				
				return tc.getProjekcije();
			}else {
				return null;
			}
		}
	
	
	@RequestMapping(value = "/prikaziBioskope/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public TheatreCinema prikaziPozoriste(@PathVariable Long id,HttpServletRequest request) {
		User us = (User)request.getSession().getAttribute("user");
		User pom = userRep.findByUserId(us.getUserId());
		TheatreCinema tc = cinemaRep.findByTcId(id);
		if(pom.getUserRole().equals(UserRole.USER)) {
			pom.getListaTC().add(tc);
			tc.getListaKorisnika().add(pom);
		}
		userRep.save(pom);
		cinemaRep.save(tc);
		return tc;
		
	}

	@RequestMapping(value = "/deleteBioskop/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteBioskop(@PathVariable Long id, HttpServletRequest request) {
		User us = (User)request.getSession().getAttribute("user");
		if(us.getUserRole().equals(UserRole.SYSADMIN)) {
			TheatreCinema c = cinemaRep.findByTcId(id);
			cinemaRep.delete(c);
			return true;
			} else {
				return false;
				}
			}
			
	@RequestMapping(value="/izmijeniBioskop/{id}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean izmijeniBioskop(@RequestBody TheatreCinema cinema, @PathVariable Long id, HttpServletRequest request) {
		User us = (User)request.getSession().getAttribute("user");
		if(us.getUserRole().equals(UserRole.SYSADMIN)) {
			TheatreCinema tc = cinemaRep.findByTcId(id);
			tc.setName(cinema.getName());
			tc.setAdress(cinema.getAdress());
			tc.setDescription(cinema.getDescription());
			cinemaRep.save(tc);
		
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
		return cinemaService.getCinema(id);
	}
	
	@RequestMapping(
			value= {"/{id}"},
			method = {RequestMethod.POST},
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void editCinema(@RequestBody TheatreCinema cinema){
		cinemaService.editCinema(cinema);
	}

	@RequestMapping(
			value= {"/BrzeKarte/{id}"},
			method = {RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TicketDTO> getFastReservationTickets(@PathVariable Long id){
		return cinemaService.getFastTickets(id);
	}

	
	
	@RequestMapping(value = "/postaviZauzetaSjedista/{sjedistaId}/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean postaviZauzetaSjedista(@PathVariable String sjedistaId,@PathVariable Long id, HttpServletRequest request) {
		
		System.out.println(sjedistaId);
		String[] pero = sjedistaId.split(" ");
		String[] nesto;
		List<Seat> listaZauzetihSjedista = new ArrayList<Seat>(); 
		System.out.println(pero);
		Projection pr = proRep.findByProjId(id);
		for(int i = 0; i < pero.length;i++) {
			 if (i%2!=0) {
				 nesto = pero[i].split(",");
				 Seat se = seatRep.findByRowNumAndColNum(Integer.parseInt(nesto[0]), Integer.parseInt(nesto[1]));
				 se.getListaProjekcija().add(pr);
				 seatRep.save(se);
				 listaZauzetihSjedista.add(se);
			 }	 
		}
	
		for(int i=0;i<listaZauzetihSjedista.size();i++) {
		
		pr.getZauzetaSjedista().add(listaZauzetihSjedista.get(i));
		}
		proRep.save(pr);
		
		return true;
		
		}
		
		
		
	
	
	@RequestMapping(value = "/zauzmiSjediste/{userId}/{projId}",
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
			Ticket t = ticketRep.findBySeatAndProjection(pr.getZauzetaSjedista().get(i),pr);
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
package isa.projekat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.model.MoviePerformance;
import isa.projekat.model.Offer;
import isa.projekat.model.Oglas;
import isa.projekat.model.RequestOglasa;
import isa.projekat.model.User;
import isa.projekat.model.UserRole;
import isa.projekat.repository.MoviePerformanceRepository;
import isa.projekat.repository.OfferRepository;
import isa.projekat.repository.OglasRepository;
import isa.projekat.repository.RequestOglasaRepository;
import isa.projekat.repository.UserRepository;
import isa.projekat.service.EmailService;

@RestController
@RequestMapping("/oglas")
public class OglasController {
	
	@Autowired
	private OglasRepository oglasRep;
	
	@Autowired
	private MoviePerformanceRepository movieRep;
	
	@Autowired
	private OfferRepository offerRep;
	
	@Autowired
	private UserRepository userRep;
	
	@Autowired
	private RequestOglasaRepository roRep;
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value = "/addOglas/{movieId}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean addOglas(@RequestBody Oglas oglas, @PathVariable Long movieId, HttpServletRequest request) {
			User us = (User) request.getSession().getAttribute("user");
			if(us.getUserRole().equals(UserRole.FANADMIN)) {
				Oglas og = null;
				og = new Oglas(oglas.getNazivOglasa(), oglas.getOpisOglasa(), oglas.getImageOglasa(), oglas.getDatumOglasa());
				MoviePerformance movie = movieRep.findByMovieId(movieId);
				movie.getOglas().add(og);
				og.getMoviePer().add(movie);
				RequestOglasa ro = new RequestOglasa();
				ro.setFanAdmin(null);
				ro.setOglas(og);
				ro.setPrihvacen(true);
				ro.setSender(us);
				og.setVlasnik(us);
				oglasRep.save(og);
				movieRep.save(movie);
				roRep.save(ro);
				userRep.save(us);
				return true;
			}else if(us.getUserRole().equals(UserRole.USER)) {
				
				Oglas og = null;
				og = new Oglas(oglas.getNazivOglasa(), oglas.getOpisOglasa(), oglas.getImageOglasa(), oglas.getDatumOglasa());
				MoviePerformance movie = movieRep.findByMovieId(movieId);
				movie.getOglas().add(og);
				og.getMoviePer().add(movie);
				RequestOglasa ro = new RequestOglasa();
				//ro.setKorisnik(us);
				ro.setOglas(og);
				ro.setPrihvacen(false);
				ro.setSender(us);
				List<User> admin = userRep.findAll();
				for(int i=0; i < admin.size();i++) {
					if(admin.get(i).getUserRole().equals(UserRole.FANADMIN)) {
					//	Hibernate.initialize(admin.get(i).getZahtjeviOglasa());
						System.out.println(admin.get(i).getUserName());
						admin.get(i).getZahtjeviOglasa().add(ro);
						ro.setFanAdmin(admin.get(i));
						
						oglasRep.save(og);
						userRep.save(admin.get(i));
						roRep.save(ro);
					}
				}
				
				oglasRep.save(og);
				userRep.save(us);
				roRep.save(ro);
				return true;
				
			}else {
				return false;
			}
	
	}
	
	@RequestMapping(value = "/prikaziOglas",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Oglas> prikaziOglas(HttpServletRequest request) {
		User us = (User)request.getSession().getAttribute("user");
		List<RequestOglasa> rp = roRep.findAll();
		List<Oglas> ogl = new ArrayList<Oglas>();
		for(int i = 0; i < rp.size();i++) {
			if(rp.get(i).isPrihvacen() && rp.get(i).getSender().getUserId().equals(us.getUserId())) {
				ogl.add(rp.get(i).getOglas());
			}
		}
		
		return ogl;
}

	@RequestMapping(value = "/prikaziOglasDrugih",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public List<Oglas> prikaziOglasDrugih(HttpServletRequest request) {
			User us = (User)request.getSession().getAttribute("user");
			List<RequestOglasa> rp = roRep.findAll();
			List<Oglas> ogl = new ArrayList<Oglas>();
			for(int i = 0; i < rp.size();i++) {
				if(rp.get(i).isPrihvacen() && !rp.get(i).getSender().getUserId().equals(us.getUserId())) {
					ogl.add(rp.get(i).getOglas());
				}
			}
			
			return ogl;
			
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
	
	@RequestMapping(value = "/getOglasRequest",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Oglas> getOglasRequest(HttpServletRequest request) {
			
			User user = (User) request.getSession().getAttribute("user");
			System.out.println("daj mi usera ovdje" + user.getUserRole());
			User us1 = userRep.findByUserId(user.getUserId());
			if(us1.getUserRole().equals(UserRole.FANADMIN)) {
				System.out.println("DODJES LI OVDJE UOPSTE");
				List<Oglas> zaPoslati = new ArrayList<Oglas>();
				
				System.out.println("SIZE" + us1.getZahtjeviOglasa().size());
				for(int i = 0; i < us1.getZahtjeviOglasa().size();i++) {
					zaPoslati.add(us1.getZahtjeviOglasa().get(i).getOglas());
					System.out.println("OGLAAAASSSIII" + us1.getZahtjeviOglasa().get(i).getOglas());
				}
				
				return zaPoslati;
			}else{
				return null;
			}
		
	}
	
	

	@RequestMapping(value = "/odbijOglas/{oglasId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Oglas odbijZahtjev(@PathVariable Long oglasId,HttpServletRequest request) {
			
			User pom = (User) request.getSession().getAttribute("user");
			User receiver = userRep.findByUserId(pom.getUserId());
			Oglas os = oglasRep.findByOglasId(oglasId);
			
			for(int i = 0; i < receiver.getZahtjeviOglasa().size(); i++) {
				if(receiver.getZahtjeviOglasa().get(i).getOglas().equals(os)) {
					receiver.getZahtjeviOglasa().remove(i);
					oglasRep.delete(os);
				}
			}
			
			userRep.save(receiver);
			return os;
	}
	
	
	@RequestMapping(value = "/prihvatiOglas/{oglasId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Oglas prihvatiZahtjev(@PathVariable Long oglasId,HttpServletRequest request) {
			
			User pom = (User) request.getSession().getAttribute("user");
			User receiver = userRep.findByUserId(pom.getUserId());
			Oglas os = oglasRep.findByOglasId(oglasId);
			RequestOglasa ro = roRep.findByRoId(os.getOglasId());
			
			for(int i = 0; i < receiver.getZahtjeviOglasa().size(); i++) {
				if(receiver.getZahtjeviOglasa().get(i).getOglas().equals(os)) {
					receiver.getZahtjeviOglasa().get(i).setPrihvacen(true);
					os.setVlasnik(ro.getSender());
					receiver.getZahtjeviOglasa().remove(i);
					ro.setFanAdmin(null);
				}
			}
		
			oglasRep.save(os);
			userRep.save(receiver);
			return os;
	}
	
	@RequestMapping(value = "/addPonuda/{oglasId}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public boolean addPonuda(@RequestBody Offer offer, @PathVariable Long oglasId, HttpServletRequest request) {
			User us = (User)request.getSession().getAttribute("user");
			User userPom = userRep.findByUserId(us.getUserId());
			Oglas og = oglasRep.findByOglasId(oglasId);
		
			Offer of = new Offer();
			of.setPonuda(offer.getPonuda());
			
			of.setSender(userPom);
			userPom.getKorisnikovePonude().add(of);
			of.setReceiver(og.getVlasnik());
			of.setPonudaOglas(og);
			og.getListaOffera().add(of);
			
		
			offerRep.save(of);
			userRep.save(userPom);
			oglasRep.save(og);
			return true;
			
			}
		
		@RequestMapping(value = "/prikaziPonude",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public List<Offer> prikaziPonude(HttpServletRequest request) {
				
			User us = (User)request.getSession().getAttribute("user");
			List<Offer> of = offerRep.findAll();
			List<Offer> pomOg = new ArrayList<Offer>();
			for(int i = 0; i < of.size();i++) {
				if(of.get(i).getSender().getUserId().equals(us.getUserId())) {
					pomOg.add(of.get(i));
				}
			}
			
			return pomOg;
			
			
			
			
			
	}
		
		
		
		@RequestMapping(value = "/prikaziPonudeDrugih",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public List<Offer> prikaziPonudeDrugih(HttpServletRequest request) {
				
			User us = (User)request.getSession().getAttribute("user");
			List<Offer> of = offerRep.findAll();
			List<Offer> pomOg = new ArrayList<Offer>();
			for(int i = 0; i < of.size();i++) {
				if(of.get(i).getReceiver().getUserId().equals(us.getUserId())) {
					pomOg.add(of.get(i));
				}
			}
			
			return pomOg;
			
	}
		
		
		

		@RequestMapping(value = "/prihvatiPonudu/{offerId}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public String prihvatiPonudu(@PathVariable Long offerId,HttpServletRequest request) {
		
			User us = (User)request.getSession().getAttribute("user");
			Offer of = offerRep.findByOfferId(offerId);
			List<Offer> ala =  offerRep.findAll();
			of.setPrihvacen(true);
			
			for(int i = 0; i < ala.size();i++) {
				if(ala.get(i).getPonudaOglas().getOglasId().equals(of.getPonudaOglas().getOglasId())) {
					if(ala.get(i).isPrihvacen()) {
						try {
							emailService.sendNotificaitionSyncPrihvacena(ala.get(i).getSender());
						} catch (MailException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						offerRep.delete(ala.get(i));
					}else {
						try {
							emailService.sendNotificaitionSyncOdbijena(ala.get(i).getSender());
						} catch (MailException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
					// TODO Auto-generated catch block
							e.printStackTrace();
						}
						offerRep.delete(ala.get(i));
					}
				}
			}
			return "nesto";
			
	}
			

		@RequestMapping(value="/izmijeniPonudu/{offerId}",
				method = RequestMethod.PUT,
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public boolean izmijeniPonudu(@RequestBody Offer offer, @PathVariable Long offerId) {
			
			Offer of = offerRep.findByOfferId(offerId);
			of.setPonuda(offer.getPonuda());
		
			offerRep.save(of);
		
				return true;

	}

		@RequestMapping(value = "/deletePonudu/{offerId}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public boolean deletePonudu(@PathVariable Long offerId) {
				Offer o = offerRep.findByOfferId(offerId);
				offerRep.delete(o);
				return true;
		}
}

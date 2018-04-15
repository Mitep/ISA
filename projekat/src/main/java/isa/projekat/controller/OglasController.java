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

import isa.projekat.model.MoviePerformance;
import isa.projekat.model.Oglas;
import isa.projekat.model.RequestOglasa;
import isa.projekat.model.User;
import isa.projekat.model.UserRole;
import isa.projekat.repository.MoviePerformanceRepository;
import isa.projekat.repository.OglasRepository;
import isa.projekat.repository.RequestOglasaRepository;
import isa.projekat.repository.UserRepository;

@RestController
@RequestMapping("/oglas")
public class OglasController {
	
	@Autowired
	private OglasRepository oglasRep;
	
	@Autowired
	private MoviePerformanceRepository movieRep;
	
	@Autowired
	private UserRepository userRep;
	
	@Autowired
	private RequestOglasaRepository roRep;
	
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
				ro.setKorisnik(null);
				ro.setOglas(og);
				ro.setPrihvacen(true);
				oglasRep.save(og);
				movieRep.save(movie);
				roRep.save(ro);
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
				
				List<User> admin = userRep.findAll();
				for(int i=0; i < admin.size();i++) {
					if(admin.get(i).getUserRole().equals(UserRole.FANADMIN)) {
					//	Hibernate.initialize(admin.get(i).getZahtjeviOglasa());
						System.out.println(admin.get(i).getUserName());
						admin.get(i).getZahtjeviOglasa().add(ro);
						ro.setKorisnik(admin.get(i));
						userRep.save(admin.get(i));
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
	public List<Oglas> prikaziOglas() {
		List<RequestOglasa> rp = roRep.findAll();
		List<Oglas> ogl = new ArrayList<Oglas>();
		for(int i = 0; i < rp.size();i++) {
			if(rp.get(i).isPrihvacen()) {
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
					receiver.getZahtjeviOglasa().remove(i);
					ro.setKorisnik(null);
				}
			}
		
			
			userRep.save(receiver);
			return os;
	}
	
}

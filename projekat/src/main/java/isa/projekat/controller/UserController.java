package isa.projekat.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.model.User;
import isa.projekat.repository.UserRepository;
import isa.projekat.service.EmailService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	private Logger logger = LoggerFactory.getLogger(UserController.class) ; 

	@Autowired
	private UserRepository userRep;
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value="/registerUser",
					method = RequestMethod.POST,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean registerUser(@RequestBody User user) {
		
		System.out.println("daj mi korisnika" + user.getUserName());
		User us = null;
		us  = new User(user.getEmail(),user.getUserPassword(),user.getUserPasswordConf(),user.getUserName(), user.getUserSurname(),user.getCity(),user.getMobileNumber(),user.getUserRole());
		us.setUserRole("ObicanKorisnik");
		if(us.getUserPassword().equals(us.getUserPasswordConf())) {
			
			//slanje emaila
			try {
				emailService.sendNotificaitionSync(us);
			}catch( Exception e ){
				logger.info("Greska prilikom slanja emaila: " + e.getMessage());
				return false;
			}

			
			userRep.save(us);
			
			return true;
		}
		
		return false;
	
	}
	
	@RequestMapping(value = "/loginUser",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean loginUser(@RequestBody User user, HttpServletRequest request) {
		User us = userRep.findByEmail(user.getEmail());
		if(us.getUserPassword().equals(user.getUserPassword())) {
			request.getSession().setAttribute("user", us);
			return true;
		}
		
			return false;
	}
	
	
	@RequestMapping(value = "/getUser",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUser(HttpServletRequest request){
		try{
			return (User)request.getSession().getAttribute("user");
		} catch (Exception e) {
			return null;
		}
		
		
	}
	
	
}

package isa.projekat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.model.User;
import isa.projekat.model.UserRole;
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
		us  = new User(user.getEmail(),user.getUserPassword(),user.getUserPasswordConf(),user.getUserName(), user.getUserSurname(),user.getCity(),user.getMobileNumber(),user.getUserRole(),user.isUserStatus());
		us.setUserRole(UserRole.USER);
		us.setUserStatus(false);
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
	
	@RequestMapping(value="/sendMail/{email}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String sendMail(@PathVariable("email") String email) {
			System.out.println("dosao ovdje");
			User user = userRep.findByEmail(email);
			user.setUserStatus(true);
			userRep.save(user);
		return "verifikovan";
	}

	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	public String checkRole(HttpServletRequest request) {
		System.out.println("Stigao sam ovdje");
		try {
			request.getSession().invalidate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "logout";
	}
	
	@RequestMapping(value = "/loginUser",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean loginUser(@RequestBody User user, HttpServletRequest request) {
		User us = userRep.findByEmail(user.getEmail());
		if(us.getUserPassword().equals(user.getUserPassword())) {
				//&& us.isUserStatus() == true) {
			//TODO : odkomentarisi kad dodje vrijeme za to
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
			System.out.println((User)request.getSession().getAttribute("user"));
			return (User)request.getSession().getAttribute("user");
		} catch (Exception e) {
			return null;
		}
		
		
	}
	
	
	@RequestMapping(value="/editUser",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean editUser(@RequestBody User user,HttpServletRequest request) {


		User us = (User)request.getSession().getAttribute("user");
		us.setEmail(user.getEmail());
		us.setUserName(user.getUserName());
		us.setUserSurname(user.getUserSurname());
		us.setUserPassword(user.getUserPassword());
		us.setUserPasswordConf(user.getUserPasswordConf());
		us.setMobileNumber(user.getMobileNumber());
		us.setCity(user.getCity());
	//	us.setUserRole("ObicanKorisnik");
		if(us.getUserPassword().equals(us.getUserPasswordConf())) {
	
			userRep.save(us);
	
			return true;
		}

		return false;

	}
	

	@RequestMapping(value = "/dodajPrijatelja/{userId}",
	method = RequestMethod.GET,
	produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean searchUsers(@PathVariable Long userId,HttpServletRequest request){
		
	//	User us = (User)request.getSession().getAttribute("user");
		
		
		return true;
	}
	
	@RequestMapping(value = "/searchUsers/{userName}/{userSurname}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
			public List<User> searchUsers(@PathVariable String userName, @PathVariable String userSurname){
				if(!userName.equals("nista") && !userSurname.equals("nista"))
					return userRep.findByUserSurnameAndUserName(userSurname, userName);
				else if(userName.equals("nista") && !userSurname.equals("nista"))
					return	userRep.findByUserSurname(userSurname);
				else if(!userName.equals("nista") && userSurname.equals("nista"))
					return userRep.findByUserName(userName);
				else
					return userRep.findAll();
				
			}
	
}

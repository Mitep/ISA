package isa.projekat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.model.MedalType;
import isa.projekat.model.RatingScale;
import isa.projekat.model.TheatreCinema;
import isa.projekat.model.User;
import isa.projekat.model.UserPassword;
import isa.projekat.model.UserRole;
import isa.projekat.repository.RatingScaleRepository;
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
	
	@Autowired
	private RatingScaleRepository rsRep;
	
	public int brojac = 0;
	
	@RequestMapping(value="/registerUser",
					method = RequestMethod.POST,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean registerUser(@RequestBody User user) {
		
		System.out.println("daj mi korisnika" + user.getUserName());
		User us = null;
		us  = new User(user.getEmail(),user.getUserPassword(),user.getUserPasswordConf(),user.getUserName(), user.getUserSurname(),user.getCity(),user.getMobileNumber(),user.getUserRole(),user.isUserStatus(), user.isFirstLogin());
		us.setUserRole(UserRole.USER);
		us.setUserStatus(false);
		us.setFirstLogin(false);
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
	public boolean checkRole(HttpServletRequest request) {
		System.out.println("Stigao sam ovdje");
		try {
			request.getSession().invalidate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}
	
	@RequestMapping(value = "/loginUser",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String loginUser(@RequestBody User user, HttpServletRequest request) {
		User us = userRep.findByEmail(user.getEmail());
		
		if(us.getUserRole().equals(UserRole.FANADMIN) && us.isFirstLogin()==false) {
		
			us.setFirstLogin(true);	
			request.getSession().setAttribute("user", us);
			
			return "nesto";
		}
		
		if(us.getUserPassword().equals(user.getUserPassword())) {
				//&& us.isUserStatus() == true) {
			//TODO : odkomentarisi kad dodje vrijeme za to
			brojac = us.getBrojLogovanja();
			brojac++;
			//System.out.println(brojac);
			us.setBrojLogovanja(brojac);
		
			userRep.save(us);
			request.getSession().setAttribute("user", us);
			
			return us.getUserRole().toString();
		}
		
			return null;
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
	

	@RequestMapping(value = "/getKorisnici",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getKorisnici(HttpServletRequest request){
		User us = (User)request.getSession().getAttribute("user");
		if(us.getUserRole().equals(UserRole.SYSADMIN)) {
			System.out.println(us.getUserRole());
			return userRep.findAll();
			
		}else {
		return null;
		}
		
	}
		
	@RequestMapping(value = "/getAdmine",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAdmine(HttpServletRequest request){
		List<User> listaKorisnika = userRep.findAll();
		List<User> pomListaKorisnika = new ArrayList<User>();
		for(int i=0; i <listaKorisnika.size(); i++) {
			if(listaKorisnika.get(i).getUserRole().equals(UserRole.ADMIN)) {
				pomListaKorisnika.add(listaKorisnika.get(i));
				
			}
		}
		return pomListaKorisnika;
		
	}
	@RequestMapping(value = "/promoteUser/{userId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> promoteUser(@PathVariable Long userId, HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		if(!user.getUserRole().equals(UserRole.SYSADMIN)) {
			return null;
		}
		User us = userRep.findByUserId(userId);
		if(us.getUserRole().equals(UserRole.USER)) {
			us.setUserRole(UserRole.ADMIN);
		} else if (us.getUserRole().equals(UserRole.ADMIN))
		{
			us.setUserRole(UserRole.FANADMIN);
		} else if (us.getUserRole().equals(UserRole.FANADMIN))
		{
			us.setUserRole(UserRole.SYSADMIN);
		} else {
			return null;
		}
		
		userRep.save(us);
		
		return userRep.findAll();
	}
	
	
	@RequestMapping(value = "/demoteUser/{userId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> demoteUser(@PathVariable Long userId, HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		if(!user.getUserRole().equals(UserRole.SYSADMIN)) {
			return null;
		}
		User us = userRep.findByUserId(userId);
		if(us.getUserRole().equals(UserRole.SYSADMIN)) {
			us.setUserRole(UserRole.FANADMIN);
		} else if (us.getUserRole().equals(UserRole.FANADMIN))
		{
			us.setUserRole(UserRole.ADMIN);
		} else if (us.getUserRole().equals(UserRole.ADMIN))
		{
			us.setUserRole(UserRole.USER);
		} else {
			return null;
		}
		
		userRep.save(us);
		
		return userRep.findAll();
	}
	
	@RequestMapping(value = "/dodajPrijatelja/{userId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean dodajPrijatelja(@PathVariable Long userId,HttpServletRequest request){
				
				User sender = (User)request.getSession().getAttribute("user");
				System.out.println("------------------");
				System.out.println(sender.getEmail());
				User reciever = (User) userRep.findByUserId(userId);
				System.out.println(reciever.getEmail());
				reciever.getFriendsRequest().add(sender);
				userRep.save(reciever);
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
	
	@RequestMapping(value="/izmijeniPassword",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean izmijeniPassword(@RequestBody UserPassword user,HttpServletRequest request) {
		System.out.println(user.getOldPassword());
		System.out.println(user.getNewPassword());
		System.out.println(user.getRepeatNewPassword());
		User us = (User)request.getSession().getAttribute("user");
		System.out.println(us.getUserRole());
		if(us.getUserRole().equals(UserRole.FANADMIN) && us.getUserPassword().equals(user.getOldPassword())) {
			if(user.getNewPassword().equals(user.getRepeatNewPassword())) {
				us.setUserPassword(user.getNewPassword());
				us.setUserPasswordConf(user.getNewPassword());
				//us.setFirstLogin(true);
				userRep.save(us);
				return true;
			}
			return false;
			
		}
		return false;
	}
	
	
	@RequestMapping(value = "/getFriendRequests",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> friendRequests(HttpServletRequest request){
				
				User user = (User)request.getSession().getAttribute("user");
				User us1 = userRep.findByUserId(user.getUserId());
				return us1.getFriendsRequest();
			}
	
	@RequestMapping(value = "/prihvatiPrijatelja/{userId}",			
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public User prihvatiZahtjev(@PathVariable Long userId,HttpServletRequest request){
			
		User pom = (User) request.getSession().getAttribute("user");
		User receiver = userRep.findByUserId(pom.getUserId());
		User sender = userRep.findByUserId(userId);
		
		receiver.getFriendsRequest().remove(sender);
		sender.getFriendsRequest().remove(receiver);
		receiver.getMyFriends().add(sender);
		userRep.save(receiver);
		
		for(int i = 0 ; i < receiver.getMyFriends().size();i++) {
			System.out.println("RECEIVER"+receiver.getMyFriends().get(i).getEmail());
			
		}
		
		for(int i = 0 ; i < sender.getMyFriends().size();i++) {
			System.out.println("SENDER" + sender.getMyFriends().get(i).getEmail());
			
		}
		
		
		for(int i = 0 ; i < receiver.getFriendsWith().size();i++) {
			System.out.println("RECEIVERFRIEND"+receiver.getFriendsWith().get(i).getEmail());
			
		}
		
		for(int i = 0 ; i < sender.getFriendsWith().size();i++) {
			System.out.println("SENDERFRIEND" + sender.getFriendsWith().get(i).getEmail());
			
		}
		
		
		return receiver;
		
		}
	
	@RequestMapping(value = "/getFriends",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> friends(HttpServletRequest request){
				
				User user = (User)request.getSession().getAttribute("user");
				User us1 = userRep.findByUserId(user.getUserId());
				List<User> temp = new ArrayList<User>();
				temp.addAll(us1.getMyFriends());
				temp.addAll(us1.getFriendsWith());
				return temp;
			}
	
	
	@RequestMapping(value = "/odbijPrijatelja/{userId}",			
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public User odbijZahtjev(@PathVariable Long userId,HttpServletRequest request){
			
		User pom = (User) request.getSession().getAttribute("user");
		User receiver = userRep.findByUserId(pom.getUserId());
		User sender = userRep.findByUserId(userId);
		
		receiver.getFriendsRequest().remove(sender);
		userRep.save(receiver);
		
		return sender;
		
		}
	
	
	@RequestMapping(value = "/obrisiPrijatelja/{userId}",			
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean obrisiPrijatelja(@PathVariable Long userId,HttpServletRequest request){
	
		
		User us = (User)request.getSession().getAttribute("user");
		User pom = userRep.findByUserId(us.getUserId());
		User u2 = userRep.findByUserId(userId);
		for(int i = 0; i < pom.getMyFriends().size(); i++) {
			System.out.println("++++" + pom.getMyFriends().get(i).getUserName());
			if(pom.getMyFriends().get(i).getUserId().equals(userId)) {
				System.out.println("aaaa");
				pom.getMyFriends().remove(i);
				
			}
		}
		
		for(int i = 0; i < pom.getFriendsWith().size(); i++) {
			System.out.println("-__-" + pom.getFriendsWith().get(i).getUserName());
			if(pom.getFriendsWith().get(i).getUserId().equals(userId)) {
				System.out.println("bbbbb");
				pom.getFriendsWith().remove(i);
				
			}
		}
		
		for(int i = 0; i < u2.getMyFriends().size(); i++) {
			System.out.println("---" + u2.getMyFriends().get(i).getUserName());
			if(u2.getMyFriends().get(i).getUserId().equals(pom.getUserId())) {
				System.out.println("aaaa---");
				u2.getMyFriends().remove(i);
				
			}
		}
		
		for(int i = 0; i < u2.getFriendsWith().size(); i++) {
			System.out.println("-__-lala" + u2.getFriendsWith().get(i).getUserName());
			if(u2.getFriendsWith().get(i).getUserId().equals(pom.getUserId())) {
				System.out.println("bbbbblala");
				u2.getFriendsWith().remove(i);
				
			}
		}
		
		userRep.save(pom);
		userRep.save(u2);
		return true;
	}
	
	
	@RequestMapping(value = "/getAdminiPozorista/{userId}",			
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public User adminiPozorista(@PathVariable Long userId,HttpServletRequest request){
			
			return userRep.findByUserId(userId);
		
		}
	
	@RequestMapping(value = "/getAdminiBioskopa/{userId}",			
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public User getAdminiBioskopa(@PathVariable Long userId,HttpServletRequest request){
			
			return userRep.findByUserId(userId);
		
		}
	
	@RequestMapping(value = "/getPozorista",			
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TheatreCinema> getPozorista(HttpServletRequest request){
			
			User us = (User) request.getSession().getAttribute("user");
			User pom = userRep.findByUserId(us.getUserId());
			return pom.getListaTC();
		
		}
	
	@RequestMapping(value = "/getBioskopi",			
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TheatreCinema> getBioskopi(HttpServletRequest request){
			
			User us = (User) request.getSession().getAttribute("user");
			User pom = userRep.findByUserId(us.getUserId());
			return pom.getListaTC();
		
		}
	
	
}

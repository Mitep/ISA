package isa.projekat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.model.MedalType;
import isa.projekat.model.RatingScale;
import isa.projekat.model.User;
import isa.projekat.model.UserRole;
import isa.projekat.repository.RatingScaleRepository;
import isa.projekat.repository.UserRepository;

@RestController
@RequestMapping("/medalje")
public class MedaljeController {
	
	@Autowired	
	private RatingScaleRepository ratingScaleRep;
	
	@Autowired 
	private UserRepository userRep;
	
	@RequestMapping(value = "/postaviSkalu",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean postaviSkalu(@RequestBody RatingScale ratingScale, HttpServletRequest request){
		User us = (User) request.getSession().getAttribute("user");
		if(us.getUserRole().equals(UserRole.SYSADMIN)) {
			RatingScale rs = ratingScaleRep.findByMedaljeId(1);
			rs.setOdBronzana(ratingScale.getOdBronzana());
			rs.setDoBronzana(ratingScale.getDoBronzana());
			rs.setOdSrebrna(ratingScale.getOdSrebrna());
			rs.setDoSrebrna(ratingScale.getDoSrebrna());
			rs.setOdZlatna(ratingScale.getOdZlatna());
			rs.setDoZlatna(ratingScale.getDoZlatna());

			ratingScaleRep.save(rs);
			
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value = "/getUserNaSkali",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUserNaSkali(HttpServletRequest request){
		
			User u = (User)request.getSession().getAttribute("user");
			RatingScale rse = ratingScaleRep.findByMedaljeId(1);
			
		//	System.out.println(rse.getOdBronzana());
			if(u.getUserRole().equals(UserRole.USER)) {
				if(u.getBrojLogovanja() > Integer.parseInt(rse.getOdBronzana()) &&  u.getBrojLogovanja() <= Integer.parseInt(rse.getDoBronzana()))
					u.setMt(MedalType.BRONZE);
				else if(u.getBrojLogovanja() > Integer.parseInt(rse.getOdSrebrna()) && u.getBrojLogovanja() <= Integer.parseInt(rse.getDoSrebrna()))
					u.setMt(MedalType.SILVER);
				else 
					u.setMt(MedalType.GOLD);
		
			userRep.save(u);
			return u;
			}else {
				return null;
			}
			
		
	}
	
	
	
	
	

}

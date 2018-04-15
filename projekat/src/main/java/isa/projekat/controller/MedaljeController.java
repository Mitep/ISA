package isa.projekat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.model.RatingScale;
import isa.projekat.model.User;
import isa.projekat.model.UserRole;
import isa.projekat.repository.RatingScaleRepository;

@RestController
@RequestMapping("/medalje")
public class MedaljeController {
	
	@Autowired	
	private RatingScaleRepository ratingScaleRep;
	
	@RequestMapping(value = "/postaviSkalu",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean postaviSkalu(@RequestBody RatingScale ratingScale, HttpServletRequest request){
		User us = (User) request.getSession().getAttribute("user");
		if(us.getUserRole().equals(UserRole.SYSADMIN)) {
			RatingScale rs = new RatingScale();
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
	

}

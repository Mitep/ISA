package isa.projekat.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.model.Hall;
import isa.projekat.model.User;
import isa.projekat.model.UserRole;
import isa.projekat.model.dtos.NewHallDTO;
import isa.projekat.model.dtos.SeatDTO;
import isa.projekat.service.HallService;

@RestController
@RequestMapping("/hall")
public class HallController {

	@Autowired
	private HallService hallService;
	
	@RequestMapping(value = "/seatState",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public String createProjection(@RequestBody SeatDTO seat){
		return hallService.getSeatState(seat);
	}
	
	@RequestMapping(value = "/allHalls/{theCinId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hall> createProjection(@PathVariable Long theCinId){
		return hallService.getAllHallsOfTheatreCinema(theCinId);
	}
	
	@RequestMapping(value = "/dodaj",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addHall(@RequestBody NewHallDTO newHallDTO, HttpServletRequest request){
		User us = (User)request.getSession().getAttribute("user");
		if(us.getUserRole().equals(UserRole.ADMIN)) {
			hallService.newHall(newHallDTO);
		}
		return null;
	}
	
	@RequestMapping(value = "/izmeni",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public String editHall(@RequestBody NewHallDTO newHallDTO, HttpServletRequest request){
		User us = (User)request.getSession().getAttribute("user");
		if(us.getUserRole().equals(UserRole.ADMIN)) {
			hallService.editHall(newHallDTO);
		}
			return null;
	}
	
	@RequestMapping(value = "/ukloni",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String removeProjection(@RequestBody Long hallId, HttpServletRequest request){
		User us = (User)request.getSession().getAttribute("user");
		if(us.getUserRole().equals(UserRole.ADMIN)) {
			hallService.deleteHall(hallId);
		}
			return null;
	}
	
	
}

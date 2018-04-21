package isa.projekat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.model.User;
import isa.projekat.model.UserRole;
import isa.projekat.model.dtos.SegmentDTO;
import isa.projekat.service.SegmentService;

@RestController
@RequestMapping("/segment")
public class SegmentController {

	@Autowired
	private SegmentService segmentService;
	
	@RequestMapping(value = "/dodaj",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String newSegment(@RequestBody SegmentDTO segment, HttpServletRequest request){
		User us = (User) request.getSession().getAttribute("user");
		if(us.getUserRole().equals(UserRole.ADMIN)) {
			segmentService.addNewSegment(segment);
			return "true";
		} else
			return null;
	}
	
	@RequestMapping(value = "/izmeni",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String editSegment(@RequestBody SegmentDTO segment, HttpServletRequest request){
		User us = (User) request.getSession().getAttribute("user");
		if(us.getUserRole().equals(UserRole.ADMIN)) {
			segmentService.editSegment(segment);
			return "true";
		} else
			return null;
	}
	
	@RequestMapping(value = "/obrisi",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteSegment(@RequestBody SegmentDTO segment, HttpServletRequest request){
		User us = (User) request.getSession().getAttribute("user");
		if(us.getUserRole().equals(UserRole.ADMIN)) {
			segmentService.deleteSegment(segment);
			return "true";
		} else
			return null;
	}
}

package isa.projekat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.model.User;
import isa.projekat.model.UserRole;
import isa.projekat.model.dtos.ProjectionDTO;
import isa.projekat.repository.ProjectionRepository;
import isa.projekat.service.ProjectionService;

@RestController
@RequestMapping("/projekcije")
public class ProjectionContoller {

	@Autowired
	private ProjectionService projectionService;
	
	@RequestMapping(value = "/dodaj",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String createProjection(@RequestBody ProjectionDTO projection, HttpServletRequest request){
		
		User us = (User) request.getSession().getAttribute("user");
		if(us.getUserRole().equals(UserRole.ADMIN)) {
		
			projectionService.createNewProjection(projection);

			return "true";
		} else
			return null;
	}
	
	@RequestMapping(value = "/{projId}",
			method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ProjectionDTO getProjection(@PathVariable Long projId){
		return projectionService.getProjection(projId);
	}
	
	
	@RequestMapping(value = "/izmeni",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String editProjection(@RequestBody ProjectionDTO projection, HttpServletRequest request){
		User us = (User) request.getSession().getAttribute("user");
		if(us.getUserRole().equals(UserRole.ADMIN)) {
			projectionService.updateProjection(projection);
			return "true";
		} else
			return null;
	}
	
	@RequestMapping(value = "/ukloni",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String removeProjection(@RequestBody Long projId){
		//System.out.println(projId);
		projectionService.deleteProjection(projId);
		return null;
	}
	
	@RequestMapping(value = "/sve/{theCinId}",
			method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProjectionDTO> getAdminsProjections(@PathVariable Long theCinId, HttpServletRequest request){
			return projectionService.getProjections(theCinId);
	}
	

	@RequestMapping(value = "/rezervisiBrzuKartu",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String editProjection(@RequestBody long ticketId, HttpServletRequest request){
		User us = (User) request.getSession().getAttribute("user");
		if(us.getUserRole().equals(UserRole.USER)) {
			projectionService.fastTicketReserve(ticketId, us.getUserId());
			return "true";
		} else
			return null;
	}
}

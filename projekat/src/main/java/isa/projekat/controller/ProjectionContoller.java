package isa.projekat.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.model.dtos.ProjectionDTO;

@RestController
@RequestMapping("/projekcije")
public class ProjectionContoller {

	
	@RequestMapping(value = "/dodaj",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ProjectionDTO createProjection(@RequestBody ProjectionDTO projection){
		
		projection.setHallName("halid");
		
		return projection;
	}
}

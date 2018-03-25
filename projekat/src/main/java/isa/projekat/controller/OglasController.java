package isa.projekat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.model.Oglas;
import isa.projekat.model.User;
import isa.projekat.repository.OglasRepository;

@RestController
@RequestMapping("/oglas")
public class OglasController {
	
	@Autowired
	private OglasRepository oglasRep;
	
	@RequestMapping(value = "/addOglas",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean addOglas(@RequestBody Oglas oglas, HttpServletRequest request) {
			User us = (User) request.getSession().getAttribute("user");
			Oglas og = null;
			og = new Oglas(oglas.getNazivOglasa(), oglas.getOpisOglasa(), oglas.getImageOglasa(), oglas.getDatumOglasa());
			oglasRep.save(og);
			return true;
	}

}

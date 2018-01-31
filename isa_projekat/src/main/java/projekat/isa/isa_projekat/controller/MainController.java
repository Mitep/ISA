package projekat.isa.isa_projekat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class MainController {
	
	@RequestMapping
	public ModelAndView getCounter() {
		
		ModelAndView model = new ModelAndView("posetime");
		model.addObject("msg", "neka poruka");
		
		return model;
	}

}

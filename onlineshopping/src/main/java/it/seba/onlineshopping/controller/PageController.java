package it.seba.onlineshopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	@RequestMapping(value = {"/", "/home", "/index"})
	public ModelAndView index() {
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting","Welcom to spring mvc");
		
		return mv;
	}
	
//  *utilizza i parametri con ? es. /test?greeting=ginogeato --> scrive gino geato
//	@RequestMapping(value="/test")      
//	public ModelAndView test(@RequestParam(value="greeting", required=false)String greeting) {
//		if (greeting == null) {
//			greeting ="Hello there";
//		}
//		ModelAndView mv = new ModelAndView("page");
//		mv.addObject("greeting",greeting);
//		
//		return mv;
//	}

//  * permette di avere l'url in chiaro ** /test/ciao mostra ciao	
//	@RequestMapping(value="/test/{greeting}")      
//	public ModelAndView test(@PathVariable("greeting")String greeting) {
//		if (greeting == null) {
//			greeting ="Hello there";
//		}
//		ModelAndView mv = new ModelAndView("page");
//		mv.addObject("greeting",greeting);
//		
//		return mv;
//	}
}

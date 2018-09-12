package es.rsoluciones.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/control")
public class RedirectController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(WebRequest request, Model model, Authentication authentication) {
	    return "login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(WebRequest request, Model model, Authentication authentication) {
	    return "registro";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showHome(WebRequest request, Model model, Authentication authentication) {
	    return "WEB-INF/pages/home";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String showAdmin(WebRequest request, Model model, Authentication authentication) {
	    return "WEB-INF/pages/admin";
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String forbidden(WebRequest request, Model model, Authentication authentication) {
	    return "WEB-INF/errorpages/403";
	}
	
}

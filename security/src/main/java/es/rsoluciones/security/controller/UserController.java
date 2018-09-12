package es.rsoluciones.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import es.rsoluciones.security.model.User;
import es.rsoluciones.security.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/accion", method = RequestMethod.GET)
	public String eliminar(WebRequest request, Model model) {
		//userService.delete(new User());
		String msj = userService.permitir(new User());
		model.addAttribute("mensaje", msj);
		return "WEB-INF/pages/home";
	}
	
	@RequestMapping(value = "/registro", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
	    User user = new User();
	    model.addAttribute("user", user);
	    return "registro";
	}
	
	@RequestMapping(value = "/registro", method = RequestMethod.POST)
	public String registerUserAccount(@ModelAttribute("user") User user, 
	      BindingResult result, WebRequest request, Errors errors) {    
	    User registered = new User();
	    if (!result.hasErrors()) {
	    	//encriptar pass
	        registered = userService.save(user);
	    }
	    if (registered == null) {
	        result.rejectValue("email", "message.regError");
	    }

	    return "login";
	}
}

package com.atos.springSecurity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControladorPrincipal {
	//@RequestMapping(value = "/loginSuccess", method = RequestMethod.POST)
	public String entrar(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// authentication.getPrincipal(); //este seria tu objeto Usuarios

		// ojo con authentication... null pointer exc
		boolean esAdmin = false;
		boolean esUser = false;
		for (GrantedAuthority ga : authentication.getAuthorities()) {
			if (ga.getAuthority().equals("ROL_ADMIN"))
				esAdmin = true;
			else if(ga.getAuthority().equals("ROL_USER"))
				esUser = true;
		}

		// no es obligatorio
		model.addAttribute("usuario", authentication.getName());

		if (esAdmin)
			return "pages/indexadmin.jsp";
		else if(esUser)
			return "pages/indexuser.jsp";
		else
			return "error/error.html";
	}
}

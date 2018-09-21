package com.atos.springSecurity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class ControladorPrincipal {
	//@RequestMapping(value = "/loginSuccess", method = RequestMethod.POST)
	public String entrar(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// authentication.getPrincipal(); //este seria tu objeto Usuarios

		// ojo con authentication.... null pointer exc
		boolean esAdmin = false;
		for (GrantedAuthority ga : authentication.getAuthorities()) {
			if (ga.getAuthority().equals("ROL_ADMIN"))
				esAdmin = true;
		}

		// no es obligatorio
		model.addAttribute("usuario", authentication.getName());

		if (esAdmin)
			return "/WEB-INF/index.jsp";
		else
			return "/error/error.html";
	}
}

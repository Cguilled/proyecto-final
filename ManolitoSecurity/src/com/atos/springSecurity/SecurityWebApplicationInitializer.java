package com.atos.springSecurity;

import javax.servlet.ServletException;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	/*public SecurityWebApplicationInitializer() {
		super(SecurityConfig.class);
	}*/

	//Para activar la sesion
	@Override
	public boolean enableHttpSessionEventPublisher() {
		return true;
	}
	
//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//	    super.onStartup(servletContext);
//	    servletContext.addListener(new SessionListener());
//	}
}

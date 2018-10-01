package com.atos.springSecurity;

import java.util.EnumSet;

import javax.servlet.ServletContext;
import javax.servlet.SessionTrackingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@EnableWebSecurity
//@Configuration
//@ImportResource("classpath:/com/atos/spring/modelo.xml")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Usuario en memoria
		//auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ROL_ADMIN");

		//Encriptado de contrasena
		auth.authenticationProvider(authProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		http
			.csrf().disable()
			.authorizeRequests().antMatchers("/login.jsp").permitAll()
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				.antMatchers("/user/**").hasAnyAuthority("ADMIN","USER")
				.antMatchers("/expired/**").hasAnyAuthority("ADMIN","USER")
				.antMatchers("/error/**").hasAnyAuthority("ADMIN","USER")
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login.jsp") //Pagina de login
				.loginProcessingUrl("/doLogin") //importante, es la url request del servlet de login de spring security (post)
				.failureUrl("/login.jsp?error")
				.successHandler(customAuthenticationSuccessHandler()) //Clase para redireccionar
				.usernameParameter("username").passwordParameter("password") //Parametros del login
			.and()
				.logout().logoutSuccessUrl("/logout").invalidateHttpSession(true)
				.logoutSuccessHandler(CustomLogoutSuccessHandler())
			.and()
				.exceptionHandling().accessDeniedPage("/error/forbidden.xhtml")
			.and()
				//proteccion contra ataques de secuestro de sesion cuando el usuario vuelve a hacer log in.
				.sessionManagement().sessionFixation().migrateSession() 
		    	.maximumSessions(1).expiredUrl("/expired/expired.xhtml"); //Pagina para cuando expire la sesion
	}
	
	//Bean de la clase para encriptar la contrasena
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Bean para encriptar la contrasena
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider daoAuth = new DaoAuthenticationProvider();
		daoAuth.setPasswordEncoder(encoder());
		daoAuth.setUserDetailsService(userService); // mi servicio que debe implementar UserDetailsService, solo se usa
													// para logueo
	    return daoAuth;
	}
	
	//Bean de la clase para redirecciones
	@Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler(){
        return new CustomUrlAuthenticationSuccessHandler();
    }
	
	//Bean de la clase para logout
	@Bean
    public LogoutSuccessHandler CustomLogoutSuccessHandler(){
        return new CustomLogoutSuccessHandler();
    }
	
	//Bean para activar el control simultaneo de la sesion
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
	
	//Injecting the Raw Session into a Controller
	/*@RequestMapping(..)
	public void fooMethod(HttpSession session) {
	    session.addAttribute(Constants.FOO, new Foo();
	    //...
	    Foo foo = (Foo) session.getAttribute(Constants.Foo);
	}*/
	
	//The current HTTP Session can also be obtained programmatically via the raw Servlet API:
	/*ServletRequestAttributes attr = (ServletRequestAttributes) 
    RequestContextHolder.currentRequestAttributes();
	HttpSession session= attr.getRequest().getSession(true);*/ // true == allow create
}
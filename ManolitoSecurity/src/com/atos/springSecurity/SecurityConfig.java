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
		/*http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/inicio.xhtml").permitAll();
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login.jsp").defaultSuccessUrl("/index.jsp");
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().defaultSuccessUrl("/WEB-INF/index.jsp");
		http.formLogin().defaultSuccessUrl("/WEB-INF/index.jsp");
		http.formLogin().failureUrl("/error/error.html");*/
		
		http
			.authorizeRequests()
				.antMatchers("/admin/").hasAuthority("ADMIN")
				.antMatchers("/user/").hasAnyAuthority("ADMIN","USER")
				.anyRequest().authenticated()
				.and()
			.formLogin().loginPage("/login.jsp") //Pagina de login
				.loginProcessingUrl("/doLogin") //importante, es la url request del servlet de login de spring security (post)
				.successHandler(customAuthenticationSuccessHandler()) //Clase para redireccionar
				.usernameParameter("username").passwordParameter("password") //Parametros del login
				.and()
				.logout().logoutSuccessUrl("/login.jsp").permitAll();
		
		//Crear sesion si se necesita
		http.sessionManagement()
			.sessionFixation().migrateSession() //proteccion contra ataques de secuestro de sesion cuando el usuario vuelve a hacer log in.
        	.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        	.maximumSessions(2).expiredUrl("/expired/expired.xhtml"); //Pagina para cuando expire la sesion
		
		//http.formLogin().defaultSuccessUrl("/loginSuccess");
		//http.logout().logoutUrl("/doLogout").logoutSuccessUrl("/login.jsp").permitAll();
		
		//http.formLogin().successHandler(customAuthenticationSuccessHandler());
		
		//http.exceptionHandling().accessDeniedPage("/control/403");
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
        return new CustomSimpleUrlAuthenticationSuccessHandler();
    }
	
	//Bean para activar el control simultaneo de la sesion
	/*@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}*/
	
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
package com.atos.springSecurity;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
public DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Usuario en memoria
		auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");

		/*UserBuilder usuario = usuario.withDefaultPasswordEncoder();
		
		// Para conexion a bbdd
		auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema().withUser(usuario.username(Usuarios.getDAS)
				.password(Usuarios.getPassword).roles(Usuario.getRoles));*/
		
		/*DaoAuthenticationProvider daoAuth = new DaoAuthenticationProvider();
		daoAuth.setPasswordEncoder(encoder());
		daoAuth.setUserDetailsService(userService); //mi servicio que debe implementar UserDetailsService, solo se usa para logueo
		auth.authenticationProvider(daoAuth);*/
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
		//http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login.jsp").defaultSuccessUrl("/index.jsp");
		//http.authorizeRequests().anyRequest().authenticated().and().formLogin().defaultSuccessUrl("/index.jsp");
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/xhtml/inicio.xhtml").permitAll();
		http.formLogin().defaultSuccessUrl("/xhtml/menuAdmin.xhtml", true);
		http.formLogin().failureUrl("/xhtml/error.html");
		http.exceptionHandling().accessDeniedPage("/xhtml/forbidden.xhtml");
		//http.csrf().disable();
	}

	/*@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}

	@Override
	public void init(WebSecurity web) throws Exception {
		super.init(web);
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}*/

	@Bean
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		InMemoryUserDetailsManager m = new InMemoryUserDetailsManager();
		m.createUser(User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build());
		return m;
	}
}

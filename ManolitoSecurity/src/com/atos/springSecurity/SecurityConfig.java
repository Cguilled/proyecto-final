package com.atos.springSecurity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
//@Configuration
//@ImportResource("classpath:/com/atos/spring/modelo.xml")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public DataSource dataSource;
	
	@Autowired
	private UserService userService;
	
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Usuario en memoria
		//auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ROL_ADMIN");

		auth.authenticationProvider(authProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
		//http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/inicio.xhtml").permitAll();
		//http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login.jsp").defaultSuccessUrl("/index.jsp");
		//http.authorizeRequests().anyRequest().authenticated().and().formLogin().defaultSuccessUrl("/WEB-INF/index.jsp");
		//http.formLogin().defaultSuccessUrl("/WEB-INF/index.jsp");
		//http.formLogin().failureUrl("/error/error.html");
	}
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider daoAuth = new DaoAuthenticationProvider();
		daoAuth.setPasswordEncoder(encoder());
		daoAuth.setUserDetailsService(userService); // mi servicio que debe implementar UserDetailsService, solo se usa
													// para logueo
	    return daoAuth;
	}

	/*@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}*/

	/*@Override
	public void init(WebSecurity web) throws Exception {
		super.init(web);
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		InMemoryUserDetailsManager m = new InMemoryUserDetailsManager();
		m.createUser(User.withDefaultPasswordEncoder().username("admin").password("admin").roles("ROL_ADMIN").build());
		return m;
	}*/

}
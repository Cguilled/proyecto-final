package es.rsoluciones.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import es.rsoluciones.security.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfiguration extends WebSecurityConfigurerAdapter {

	// faltan los filtros de sesiones y demas

	@Autowired
	private UserService userService;

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// provider de autenticacion, el servicio de gestion de usuarios y el password
		// encoder
		DaoAuthenticationProvider daoAuth = new DaoAuthenticationProvider();
		daoAuth.setPasswordEncoder(encoder());
		daoAuth.setUserDetailsService(userService);
		auth.authenticationProvider(daoAuth);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/user/registro").permitAll().anyRequest().authenticated();
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated();
		http.authorizeRequests().antMatchers("/pages/**").hasAnyRole("ADMIN","USER").anyRequest().authenticated();
		http.formLogin().loginPage("/login.jsp").permitAll();
		http.formLogin().loginProcessingUrl("/doLogin").permitAll(); //importante, es la url request del servlet de login de spring security (post)
		http.formLogin().successHandler(customAuthenticationSuccessHandler());
		http.formLogin().usernameParameter("usuario").passwordParameter("password");
		http.logout().logoutUrl("/doLogout").logoutSuccessUrl("/login.jsp").permitAll();
		http.exceptionHandling().accessDeniedPage("/control/403");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**"); // #3
	}
	
	@Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler(){
        return new CustomSimpleUrlAuthenticationSuccessHandler();
    }
}

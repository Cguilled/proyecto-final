package com.atos.springSecurity;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.atos.hibernate.dto.Roles;
import com.atos.hibernate.dto.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;

@Service
public class UserService implements UserDetailsService {
	
	// Inyecto un objecto de gestionUsuarios
	@ManagedProperty("#{gestionUsuarios}")
	private IGestion_Usuarios gestionUsuarios;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String das) throws UsernameNotFoundException {
		Usuarios usuario = gestionUsuarios.consultar_PorDas(das);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		// authorities a partir de roles
		Roles roles = usuario.getRoles();

		GrantedAuthority ga = new GrantedAuthority() {

			@Override
			public String getAuthority() {
				return roles.getNombreRol();
			}
		};
		authorities.add(ga);

		UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(usuario.getDas(),
				encoder.encode(usuario.getPassword()), authorities);

		return userDetails;

	}

	public void setGestionUsuarios(IGestion_Usuarios gestionUsuarios) {
		this.gestionUsuarios = gestionUsuarios;
	}
	
}
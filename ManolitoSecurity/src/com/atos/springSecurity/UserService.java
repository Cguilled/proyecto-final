package com.atos.springSecurity;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;

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

	private Roles roles;
	UserDetails userDetails;

	@Override
	public UserDetails loadUserByUsername(String das) throws UsernameNotFoundException {
		Usuarios usuario = gestionUsuarios.consultar_PorDas(das);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		// authorities a partir de roles
		if (usuario != null) {
			roles = usuario.getRoles();

			GrantedAuthority ga = new GrantedAuthority() {

				@Override
				public String getAuthority() {
					return roles.getNombreRol();
				}
			};
			authorities.add(ga);

			userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(
					usuario.getDas(), encoder.encode(usuario.getPassword()), authorities);
			
		} else
			System.out.println("no existe el usuario");

		return userDetails;

	}

	public void setGestionUsuarios(IGestion_Usuarios gestionUsuarios) {
		this.gestionUsuarios = gestionUsuarios;
	}

}
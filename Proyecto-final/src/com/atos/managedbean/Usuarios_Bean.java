package com.atos.managedbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.dao.DataAccessException;

import com.atos.hibernate.dto.Roles;
import com.atos.hibernate.dto.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;

/**
 * 
 * @author Guillermo Cermeño
 *
 * 09 ago. 2018
 */

@ManagedBean(name = "usuarios_bean")
@ViewScoped
public class Usuarios_Bean implements Serializable{
	private Usuarios usuario;
	private boolean visible;

	@ManagedProperty("#{gestionUsuarios}")
	private IGestion_Usuarios gestionUsuarios;
	
	@PostConstruct
	public void valores_Iniciales() {
		usuario = new Usuarios();
		usuario.setDas("");
		usuario.setNombre("");
		usuario.setApellido("");
		usuario.setEstado("");
		usuario.setPassword("");
		usuario.setInicio("true");
		usuario.setRoles(new Roles(0));
		visible = true;
		
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	// EVENTOS
	public void alta_Usuario(ActionEvent evento) {
		try {
			gestionUsuarios.alta_Usuario(usuario);
			System.out.println("alta correcta");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public void baja_Usuario(ActionEvent evento) {
		try {
			// NO SE TIENE QUE ELIMINAR EL USUARIO SINO CAMBIAR EL CAMPO ESTADO A NO ACTIVO
			usuario.setEstado("inactivo");
			gestionUsuarios.modificacion_Usuario(usuario);
			System.out.println("baja correcta");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public void modificacion_Usuario(ActionEvent evento) {
		try {
			gestionUsuarios.modificacion_Usuario(usuario);
			System.out.println("modificación correcta");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public void setGestionUsuarios(IGestion_Usuarios gestionUsuarios) {
		this.gestionUsuarios = gestionUsuarios;
	}
}

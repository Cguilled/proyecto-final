package com.atos.managedbean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.dao.DataAccessException;

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
public class Usuarios_Bean {
	private String nombre_usuario;
	private String nombre;
	private String apellido;
	private String estado;
	private String password;
	private Usuarios usuario;

	@ManagedProperty("#{gestionUsuarios}")
	private IGestion_Usuarios gestionUsuarios;
	
	@PostConstruct
	public void valores_Iniciales() {
		usuario = new Usuarios();
		nombre_usuario = "";
		nombre = "";
		apellido = "";
		estado = "";
		password = "";
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
			gestionUsuarios.baja_Usuario(usuario);
			System.out.println("baja correcta");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public void modificacion_Usuario(ActionEvent evento) {
		try {
			gestionUsuarios.modificacion_Usuario(usuario);
			System.out.println("modificación correcta");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGestionUsuarios(IGestion_Usuarios gestionUsuarios) {
		this.gestionUsuarios = gestionUsuarios;
	}
}

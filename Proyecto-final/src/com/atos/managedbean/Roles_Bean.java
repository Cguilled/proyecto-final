package com.atos.managedbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.dao.DataAccessException;

import com.atos.hibernate.dto.Roles;
import com.atos.hibernate.dto.Tareas;
import com.atos.hibernate.modelo.IGestion_Roles;
import com.atos.hibernate.modelo.IGestion_Tareas;

/**
 * 
 * @author Guillermo Cermeño
 *
 * 09 ago. 2018
 */

@ManagedBean(name = "roles_bean")
@ViewScoped
public class Roles_Bean implements Serializable{
	private Roles rol;
	
	@ManagedProperty("#{gestion_roles}")
	private IGestion_Roles gestionRoles;

	@PostConstruct
	public void valores_Iniciales() {
		System.out.println("cargando...");
		rol = new Roles();
		rol.setNombre_rol("");
	}
	
	private void reset_valores() {
		rol.setNombre_rol("");
	}
	
	//FALTAN LOS METODOS DE LA FACHADA, SIN ELLOS AQUÍ NO SE HACE NADA...
	// EVENTOS
		public void alta_Rol(ActionEvent evento) {
			try {
				System.out.println("Haciendo alta");
				gestionRoles.alta_Rol(rol);
				reset_valores();
			} catch (DataAccessException dae) {
				dae.printStackTrace();
			}
		}

		public void baja_Rol(ActionEvent evento) {
			try {
				gestionRoles.baja_Rol(rol);
				System.out.println("baja correcta");
				reset_valores();
			} catch (DataAccessException dae) {
				dae.printStackTrace();
			}
		}

		public void modificacion_Rol(ActionEvent evento) {
			try {
				gestionRoles.modificacion_Rol(rol);
				System.out.println("modificación correcta");
				reset_valores();
			} catch (DataAccessException dae) {
				dae.printStackTrace();
			}
		}

		public void consultar_PorCodigo(ActionEvent evento) {
			try {
				gestionRoles.consultar_PorCodigoRol(rol);
				System.out.println("consulta correcta");
			} catch (DataAccessException dae) {
				dae.printStackTrace();
			}
		}

	public Roles getRol() {
		return rol;
	}

	public void setRol(Roles rol) {
		this.rol = rol;
	}

	public void setGestionRoles(IGestion_Roles gestionRoles) {
		this.gestionRoles = gestionRoles;
	}
}

package com.atos.managedbean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.dao.DataAccessException;

import com.atos.hibernate.dto.Tareas;
import com.atos.hibernate.dto.Usuarios;
import com.atos.hibernate.modelo.IGestion_Tareas;
import com.atos.hibernate.modelo.IGestion_Usuarios;

/**
 * 
 * @author Guillermo Cermeño
 *
 * 09 ago. 2018
 */

@ManagedBean(name = "tareas_bean")
@ViewScoped
public class Tareas_Bean {
	private Tareas tarea;
	private String nombre_tarea;
	private String descripcion_tareas;
	private Integer estado;

	@ManagedProperty("#{gestion_tareas}")
	private IGestion_Tareas gestionTareas;

	@PostConstruct
	public void valores_Iniciales() {
		tarea = new Tareas();
		nombre_tarea = "";
		descripcion_tareas = "";
		estado = 1;
	}

	//FALTAN LOS METODOS DE LA FACHADA, SIN ELLOS AQUÍ NO SE HACE NADA...
	// EVENTOS
	public void alta_Tarea(ActionEvent evento) {
		try {
			gestionTareas.alta_Tarea(tarea);
			System.out.println("alta correcta");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public void baja_Tarea(ActionEvent evento) {
		try {
			// NO SE TIENE QUE ELIMINAR LA TAREA SINO CAMBIAR EL CAMPO ESTADO A 0
			gestionTareas.baja_Tarea(tarea);
			System.out.println("baja correcta");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public void modificacion_Tarea(ActionEvent evento) {
		try {
			gestionTareas.modificacion_Tarea(tarea);
			System.out.println("modificación correcta");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public void consultar_PorCodigo(ActionEvent evento) {
		try {
			gestionTareas.consultar_PorCodigoTarea(tarea);
			System.out.println("consulta correcta");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public Tareas getTarea() {
		return tarea;
	}

	public void setTarea(Tareas tarea) {
		this.tarea = tarea;
	}

	public String getNombre_tarea() {
		return nombre_tarea;
	}

	public void setNombre_tarea(String nombre_tarea) {
		this.nombre_tarea = nombre_tarea;
	}

	public String getDescripcion_tareas() {
		return descripcion_tareas;
	}

	public void setDescripcion_tareas(String descripcion_tareas) {
		this.descripcion_tareas = descripcion_tareas;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public void setGestionTareas(IGestion_Tareas gestionTareas) {
		this.gestionTareas = gestionTareas;
	}
}
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
	private Integer estado;
	private boolean visible;

	@ManagedProperty("#{gestion_tareas}")
	private IGestion_Tareas gestionTareas;

	@PostConstruct
	public void valores_Iniciales() {
		tarea = new Tareas();
		tarea.setNombre_Tarea("");
		tarea.setDescripcion_Tarea("");
		estado = 1;
		tarea.setEstado(true);
		visible=true;
	}

	//FALTAN LOS METODOS DE LA FACHADA, SIN ELLOS AQUÍ NO SE HACE NADA...
	// EVENTOS
	public void alta_Tarea(ActionEvent evento) {
		try {
			//tarea.setEstado(this.estado == 1);
			gestionTareas.alta_Tarea(tarea);
			System.out.println("alta correcta");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public void baja_Tarea(ActionEvent evento) {
		try {
			// NO SE TIENE QUE ELIMINAR LA TAREA SINO CAMBIAR EL CAMPO ESTADO A 0
			//tarea.setEstado(this.estado == 1);
			gestionTareas.baja_Tarea(tarea);
			System.out.println("baja correcta");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public void modificacion_Tarea(ActionEvent evento) {
		try {
			//tarea.setEstado(this.estado == 1);
			gestionTareas.modificacion_Tarea(tarea);
			System.out.println("modificación correcta");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public void consultar_PorCodigo(ActionEvent evento) {
		try {
			//tarea.setEstado(this.estado == 1);
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

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setGestionTareas(IGestion_Tareas gestionTareas) {
		this.gestionTareas = gestionTareas;
	}
}
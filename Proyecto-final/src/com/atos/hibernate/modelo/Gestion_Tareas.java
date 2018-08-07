package com.atos.hibernate.modelo;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.atos.hibernate.Usuarios;
import com.atos.hibernate.dao.ext.UsuarioDAO_EXT;


@Component("gestion_tareas")
@Scope("prototype")
public class Gestion_Tareas implements IGestion_Tareas {

	private TareaDAO tarea_dao;

	// ***************** CONSULTAS
	@Override
	@Transactional(readOnly = true)
	public Tareas consultar_PorCodigoTarea(int codigo_tarea) {
		return tarea_dao.findById(codigo_tarea);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tareas> consultar_Todos() {
		return tarea_dao.findAll();
	}
	
	// ************ CRUD
	@Override
	@Transactional
	public void alta_Tarea(Tarea tarea) {
		tarea_dao.save(tarea);
	}

	@Override
	@Transactional
	public void baja_Tarea(Tarea tarea) {
		tarea_dao.delete(tarea);
	}

	@Override
	@Transactional
	public void modificacion_Tarea(Tarea tarea) {
		tarea_dao.attachDirty(tarea);
	}

	// ACCESORES DE SPRING
	public void setTarea_dao(TareaDAO tarea_dao) {
		this.tarea_dao = tarea_dao;
	}

}

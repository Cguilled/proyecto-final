package com.atos.hibernate.modelo;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.atos.hibernate.Roles;



public interface IGestion_Tareas {

	public List<Roles> consultar_Todos();

}
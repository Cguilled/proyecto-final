package com.atos.hibernate.modelo;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.atos.hibernate.dao.RolesDAO;
import com.atos.hibernate.dto.Roles;

@Component("gestion_roles")
@Scope("prototype")
public class Gestion_Roles implements IGestion_Roles {

	private RolesDAO rol_dao;

	@Override
	@Transactional(readOnly = true)
	public List<Roles> consultar_Todos() {
		return rol_dao.findAll();
	}

	// ACCESORS PARA SPRING
	public void setRol_dao(RolesDAO rol_dao) {
		this.rol_dao = rol_dao;
	}

}

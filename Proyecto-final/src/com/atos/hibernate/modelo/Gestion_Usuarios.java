package com.atos.hibernate.modelo;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.atos.hibernate.Usuarios;
import com.atos.hibernate.dao.ext.UsuarioDAO_EXT;


@Component("gestion_usuarios")
@Scope("prototype")
public class Gestion_Usuarios implements IGestion_Usuarios {

	private UsuarioDAO_EXT usuario_dao;

	// ***************** CONSULTAS
	@Override
	@Transactional(readOnly = true)
	public Usuarios consultar_PorIdNombre(int id_usuario) {
		return usuario_dao.findById(id_usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuarios> consultar_Todos() {
		return usuario_dao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuarios consultar_PorClaveYDAS() {
		return usuario_dao.consultar_PorClaveYDAS();
	}
	
	// ************ CRUD
	@Override
	@Transactional
	public void alta_Usuario(Usuarios usuario) {
		usuario_dao.save(usuario);
	}

	@Override
	@Transactional
	public void baja_Usuario(Usuarios usuario) {
		usuario_dao.delete(usuario);
	}

	@Override
	@Transactional
	public void modificacion_Usuario(Usuarios usuario) {
		usuario_dao.attachDirty(usuario);
	}

	// ACCESORES DE SPRING
	public void setUsuario_dao(UsuarioDAO_EXT usuario_dao) {
		this.usuario_dao = usuario_dao;
	}

}

package com.atos.hibernate.modelo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.atos.hibernate.dto.Usuarios;
import com.atos.hibernate.dao.UsuariosDAO;



/**
 * Fachada de acceso a los procesos de los DAO.
 */
@Component("gestionUsuarios")
@Scope("prototype")
public class Gestion_Usuarios implements IGestion_Usuarios {

	// DAO DE ACCESO A LA INFORMACION
	private UsuariosDAO usuarios_dao;

	/**
	 * Constructor de la fachada.
	 */
	public Gestion_Usuarios() {
		usuarios_dao = new UsuariosDAO();
	}
	
	// ***************** CONSULTAS
	@Override
	@Transactional(readOnly = true)
	public Usuarios consultar_PorIdNombre(int id_usuario) {
		return usuarios_dao.findById(id_usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuarios> consultar_Todos() {
		return usuarios_dao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuarios consultar_PorClaveYDAS(String das, String clave) {

		List<String> properties=new ArrayList<String>(){{add("DAS");add("PASSWORD");}};
		List<Object> values=new ArrayList<Object>(){{add(das);add(clave);}};
		
		return (Usuarios)usuarios_dao.findByProperty(properties, values).get(0);
		
	}
	
	// ************ CRUD
	@Override
	@Transactional
	public void alta_Usuario(Usuarios usuario) {
		usuarios_dao.save(usuario);
	}

	@Override
	@Transactional
	public void baja_Usuario(Usuarios usuario) {
		usuarios_dao.delete(usuario);
	}

	@Override
	@Transactional
	public void modificacion_Usuario(Usuarios usuario) {
		usuarios_dao.attachDirty(usuario);
	}

	// ACCESORES DE SPRING
	public void setusuarios_dao(UsuariosDAO usuario_dao) {
		this.usuarios_dao = usuario_dao;
	}

}

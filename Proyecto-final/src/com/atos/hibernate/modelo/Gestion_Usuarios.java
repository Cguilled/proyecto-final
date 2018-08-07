package com.atos.hibernate.modelo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.atos.hibernate.dto.Usuarios;
import com.atos.hibernate.dao.UsuariosDAO;

@Component("gestion_usuarios")
@Scope("prototype")

/**
 * Fachada de acceso a los procesos de los DAO.
 */
public class Gestion_Usuarios implements IGestion_Usuarios {

	// DAO DE ACCESO A LA INFORMACION
	private UsuariosDAO usuario_DAO;

	/**
	 * Constructor de la fachada.
	 */
	public Gestion_Usuarios() {
		usuario_DAO = new UsuariosDAO();
	}
	
	// ***************** CONSULTAS
	@Override
	@Transactional(readOnly = true)
	public Usuarios consultar_PorIdNombre(int id_usuario) {
		return usuario_DAO.findById(id_usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuarios> consultar_Todos() {
		return usuario_DAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuarios consultar_PorClaveYDAS(int das, String clave) {

		List<String> properties=new ArrayList<String>(){{add("DAS");add("PASSWORD");}};
		List<Object> values=new ArrayList<Object>(){{add(das);add(clave);}};
		
		return (Usuarios)usuario_DAO.findByProperty(properties, values).get(0);
		
	}
	
	// ************ CRUD
	@Override
	@Transactional
	public void alta_Usuario(Usuarios usuario) {
		usuario_DAO.save(usuario);
	}

	@Override
	@Transactional
	public void baja_Usuario(Usuarios usuario) {
		usuario_DAO.delete(usuario);
	}

	@Override
	@Transactional
	public void modificacion_Usuario(Usuarios usuario) {
		usuario_DAO.attachDirty(usuario);
	}

	// ACCESORES DE SPRING
	public void setUsuario_dao(UsuariosDAO usuario_dao) {
		this.usuario_DAO = usuario_dao;
	}

}

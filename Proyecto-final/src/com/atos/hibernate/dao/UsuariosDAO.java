package com.atos.hibernate.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.atos.hibernate.dto.Usuarios;

@Repository("usuarios_dao")
@Scope("prototype")
public class UsuariosDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UsuariosDAO.class);
	// modificar esto en cuanto tengamos acceso a las variables
	public static final String ID_USUARIO = "id";
	public static final String DAS = "das";
	public static final String NOMBRE = "nombre";
	public static final String APELLIDO = "apellido";
	public static final String PASSWORD = "password";
	public static final String ESTADO = "estado";
	public static final String INICIO = "inicio";
	public static final String CODIGO_ROL = "codigo_rol";

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void save(Usuarios transientInstance) {
		log.debug("saving Usuarios instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void delete(Usuarios persistentInstance) {
		log.debug("deleting Usuarios instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Usuarios findById(java.lang.Integer id) {
		log.debug("getting Usuarios instance with id: " + id);
		try {
			Usuarios instance = (Usuarios) getCurrentSession().get(
					"com.atos.hibernate.Tareas", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Usuarios instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Usuarios as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Usuarios> findByDas(Object nombre) {
		return findByProperty(DAS, nombre);
	}
	
	public List<Usuarios> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List<Usuarios> findByApellido(Object descripcion) {
		return findByProperty(APELLIDO, descripcion);
	}

	public List<Usuarios> findByEstado(Object descripcion) {
		return findByProperty(ESTADO, descripcion);
	}
	
	public List<Usuarios> findByInicio(Object descripcion) {
		return findByProperty(INICIO, descripcion);
	}
	
	public List<Usuarios> findByRol(Object descripcion) {
		return findByProperty(CODIGO_ROL, descripcion);
	}

	public List findAll() {
		log.debug("finding all Usuarios instances");
		try {
			String queryString = "from Tareas";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Usuarios merge(Usuarios detachedInstance) {
		log.debug("merging Usuarios instance");
		try {
			Usuarios result = (Usuarios) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Usuarios instance) {
		log.debug("attaching dirty Usuarios instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Usuarios instance) {
		log.debug("attaching clean Usuarios instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static Usuarios getFromApplicationContext(ApplicationContext ctx) {
		return (Usuarios) ctx.getBean("UsuariosDAO");
	}
}

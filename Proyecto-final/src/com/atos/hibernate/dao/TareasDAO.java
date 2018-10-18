package com.atos.hibernate.dao;

import org.springframework.stereotype.Repository;

import com.atos.hibernate.dto.Tareas;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

@Repository("tareas_dao")
@Scope("prototype")
public class TareasDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TareasDAO.class);
	// modificar esto en cuanto tengamos acceso a las variables
	public static final String ID_TAREA = "id";
	public static final String NOMBRE_TAREA = "nombre";
	public static final String DESCRIPCION_TAREA = "descripcion";
	public static final String ESTADO = "estado";

	private SessionFactory sessionFactory;
	
	//Spring iniciará el SessionFactory
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//Guardar un objeto de tipo Tareas en la tabla Roles
	public void save(Tareas transientInstance) {
		log.debug("saving Tareas instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	//Borrar un valor de Tareas de la tabla que sea identico a nuestro objeto roles
	public void delete(Tareas persistentInstance) {
		log.debug("deleting Tareas instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	//Recuperar un objeto de tipo Tareas a partir de un Id
	public Tareas findById(java.lang.Integer id) {
		log.debug("getting Tareas instance with id: " + id);
		try {
			Tareas instance = (Tareas) getCurrentSession().get(
					"com.atos.hibernate.dto.Tareas", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//Encontrar todos los objetos de tipo Tareas que tengan una cierta propiedad
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Tareas instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tareas as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	//Recuperar objetos de tipo tareas a partir de un nombre
	public List<Tareas> findByNombreTareas(Object nombre) {
		return findByProperty(NOMBRE_TAREA, nombre);
	}
	
	//Recuperar objetos de tipo tareas a partir de una descripcion
	public List<Tareas> findByDescripcionTareas(Object descripcion) {
		return findByProperty(DESCRIPCION_TAREA, descripcion);
	}
	
	//Recuperar objetos de tipo tareas a partir de un estado
	public List<Tareas> findByEstadoTareas(Object estado) {
		return findByProperty(ESTADO, estado);
	}

	//Recuperar todos los objetos de tipo tareas
	public List findAll() {
		log.debug("finding all Tareas instances");
		try {
			String queryString = "from Tareas";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	//Hace una consulta para actualizar un objeto de tipo Tareas desde la base de datos a nuestra aplicación
	public Tareas merge(Tareas detachedInstance) {
		log.debug("merging Tareas instance");
		try {
			Tareas result = (Tareas) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	//Hace un update para actualizar un objeto de tipo Tareas desde nuestra aplicación a la base de datos (e intenta bloquear)
	public void attachDirty(Tareas instance) {
		log.debug("attaching dirty Tareas instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	//Hace un update para actualizar un objeto de tipo Tareas desde nuestra aplicación a la base de datos (sin intentar bloquear)
	public void attachClean(Tareas instance) {
		log.debug("attaching clean Tareas instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	//Devuelve un valor Tareas de ApplicationContext
	public static Tareas getFromApplicationContext(ApplicationContext ctx) {
		return (Tareas) ctx.getBean("TareasDAO");
	}
	
}

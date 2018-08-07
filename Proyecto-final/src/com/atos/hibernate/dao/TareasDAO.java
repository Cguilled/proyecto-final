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
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void save(Tareas transientInstance) {
		log.debug("saving Articulos instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void delete(Tareas persistentInstance) {
		log.debug("deleting Articulos instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tareas findById(java.lang.Integer id) {
		log.debug("getting Articulos instance with id: " + id);
		try {
			Tareas instance = (Tareas) getCurrentSession().get(
					"com.atos.hibernate.Tareas", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Articulos instance with property: " + propertyName
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

	public List<Tareas> findByNombreTareas(Object nombre) {
		return findByProperty(NOMBRE_TAREA, nombre);
	}

	public List<Tareas> findByDescripcionTareas(Object descripcion) {
		return findByProperty(DESCRIPCION_TAREA, descripcion);
	}

	public List<Tareas> findByEstadoTareas(Object estado) {
		return findByProperty(ESTADO, estado);
	}

	public List findAll() {
		log.debug("finding all Articulos instances");
		try {
			String queryString = "from Tareas";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tareas merge(Tareas detachedInstance) {
		log.debug("merging Articulos instance");
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

	public void attachDirty(Tareas instance) {
		log.debug("attaching dirty Articulos instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tareas instance) {
		log.debug("attaching clean Articulos instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static Tareas getFromApplicationContext(ApplicationContext ctx) {
		return (Tareas) ctx.getBean("ArticulosDAO");
	}
	
}

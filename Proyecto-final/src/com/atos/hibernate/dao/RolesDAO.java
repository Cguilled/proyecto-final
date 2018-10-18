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

import com.atos.hibernate.dto.Roles;

@Repository("roles_dao")
@Scope("prototype")
public class RolesDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RolesDAO.class);
	// modificar esto en cuanto tengamos acceso a las variables
	public static final String CODIGO_ROL = "codigo_rol";
	public static final String DESCRIPCION_ROL = "descripcion";

	private SessionFactory sessionFactory;
	
	//Spring iniciará el SessionFactory
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//Guardar un objeto de tipo Roles en la tabla Roles
	public void save(Roles transientInstance) {
		log.debug("saving Roles instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	//Borrar un valor de roles de la tabla que sea identico a nuestro objeto roles
	public void delete(Roles persistentInstance) {
		log.debug("deleting Roles instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	//Recuperar un objeto de tipo roles a partir de un Id
	public Roles findByCodigoRol(java.lang.Integer id) {
		log.debug("getting Roles instance with id: " + id);
		try {
			Roles instance = (Roles) getCurrentSession().get(
					"com.atos.hibernate.dto.Roles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//Encontrar todos los objetos de tipo roles que tengan una cierta propiedad
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Roles instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Roles as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	//Recuperar objetos de tipo roles a partir de una descripcion
	public List<Roles> findByDescripcion(Object nombre) {
		return findByProperty(DESCRIPCION_ROL, nombre);
	}
	
	//Recuperar todos los objetos de tipo roles
	public List findAll() {
		log.debug("finding all Roles instances");
		try {
			String queryString = "from Roles";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	//Hace una consulta para actualizar un objeto de tipo roles desde la base de datos a nuestra aplicación
	public Roles merge(Roles detachedInstance) {
		log.debug("merging Roles instance");
		try {
			Roles result = (Roles) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	//Hace un update para actualizar un objeto de tipo roles desde nuestra aplicación a la base de datos (e intenta bloquear)
	public void attachDirty(Roles instance) {
		log.debug("attaching dirty Roles instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	//Hace un update para actualizar un objeto de tipo roles desde nuestra aplicación a la base de datos (sin intentar bloquear)
	public void attachClean(Roles instance) {
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
	
	//Devuelve un valor Roles de ApplicationContext
	public static Roles getFromApplicationContext(ApplicationContext ctx) {
		return (Roles) ctx.getBean("RolesDAO");
	}

}

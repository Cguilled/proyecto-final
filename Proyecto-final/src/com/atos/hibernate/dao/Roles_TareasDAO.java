//package com.atos.hibernate.dao;
//
//import java.util.List;
//
//import org.hibernate.LockOptions;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Repository;
//
//@Repository("roles_tareas_dao")
//@Scope("prototype")
//public class Roles_TareasDAO {
//	private static final Logger log = LoggerFactory
//			.getLogger(Roles_TareasDAO.class);
//	// modificar esto en cuanto tengamos acceso a las variables
//	public static final String CODIGO_ROL = "codigo_rol";
//	public static final String CODIGO_TAREA = "codigo_tarea";
//
//	private SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
//
//	protected Session getCurrentSession() {
//		return sessionFactory.getCurrentSession();
//	}
//	
//	public void save(Roles_Tareas transientInstance) {
//		log.debug("saving Roles instance");
//		try {
//			getCurrentSession().save(transientInstance);
//			log.debug("save successful");
//		} catch (RuntimeException re) {
//			log.error("save failed", re);
//			throw re;
//		}
//	}
//	
//	public void delete(Roles_Tareas persistentInstance) {
//		log.debug("deleting Roles instance");
//		try {
//			getCurrentSession().delete(persistentInstance);
//			log.debug("delete successful");
//		} catch (RuntimeException re) {
//			log.error("delete failed", re);
//			throw re;
//		}
//	}
//
//	public Roles_Tareas findByCodigoRol(java.lang.Integer id) {
//		log.debug("getting Roles instance with id: " + id);
//		try {
//			Roles_Tareas instance = (Roles_Tareas) getCurrentSession().get(
//					"com.atos.hibernate.Tareas", id);
//			return instance;
//		} catch (RuntimeException re) {
//			log.error("get failed", re);
//			throw re;
//		}
//	}
//	
//	public List findByProperty(String propertyName, Object value) {
//		log.debug("finding Roles instance with property: " + propertyName
//				+ ", value: " + value);
//		try {
//			String queryString = "from Roles as model where model."
//					+ propertyName + "= ?";
//			Query queryObject = getCurrentSession().createQuery(queryString);
//			queryObject.setParameter(0, value);
//			return queryObject.list();
//		} catch (RuntimeException re) {
//			log.error("find by property name failed", re);
//			throw re;
//		}
//	}
//
//	public List<Roles_Tareas> findByCodigoTareas(Object nombre) {
//		return findByProperty(CODIGO_TAREA, nombre);
//	}
//
//	public List findAll() {
//		log.debug("finding all Usuarios instances");
//		try {
//			String queryString = "from Tareas";
//			Query queryObject = getCurrentSession().createQuery(queryString);
//			return queryObject.list();
//		} catch (RuntimeException re) {
//			log.error("find all failed", re);
//			throw re;
//		}
//	}
//
//	public Roles_Tareas merge(Roles_Tareas detachedInstance) {
//		log.debug("merging Usuarios instance");
//		try {
//			Roles_Tareas result = (Roles_Tareas) getCurrentSession().merge(
//					detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}
//
//	public void attachDirty(Roles_Tareas instance) {
//		log.debug("attaching dirty Roles instance");
//		try {
//			getCurrentSession().saveOrUpdate(instance);
//			log.debug("attach successful");
//		} catch (RuntimeException re) {
//			log.error("attach failed", re);
//			throw re;
//		}
//	}
//
//	public void attachClean(Roles_Tareas instance) {
//		log.debug("attaching clean Usuarios instance");
//		try {
//			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
//					instance);
//			log.debug("attach successful");
//		} catch (RuntimeException re) {
//			log.error("attach failed", re);
//			throw re;
//		}
//	}
//
//	public static Roles_Tareas getFromApplicationContext(ApplicationContext ctx) {
//		return (Roles_Tareas) ctx.getBean("RolesDAO");
//	}
//}

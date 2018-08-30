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
	public static final String DAS = "das";
	public static final String NOMBRE = "nombre";
	public static final String APELLIDO = "apellido";
	public static final String PASSWORD = "password";
	public static final String ESTADO = "estado";
	public static final String INICIO = "inicio";
	public static final String CODIGO_ROL = "codigo_rol";

	private SessionFactory sessionFactory;
	
	//Spring iniciará el SessionFactory
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//Guardar un objeto de tipo Usuarios en la tabla Roles
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
	
	//Borrar un valor de Usuarios de la tabla que sea identico a nuestro objeto roles
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

	//Recuperar un objeto de tipo Usuarios a partir de un Id
	public Usuarios findById(java.lang.Integer id) {
		log.debug("getting Usuarios instance with id: " + id);
		try {
			Usuarios instance = (Usuarios) getCurrentSession().get(
					"com.atos.hibernate.dto.Usuarios", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//Encontrar todos los objetos de tipo Usuarios que tengan una cierta propiedad
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
	
	//Encontrar todos los objetos de tipo Usuarios que tengan ciertas propiedades
	public List findByProperty(List<String> propertiesNames, List<Object> values) {
		
		log.debug("finding Usuarios instance with properties");
		try {
			
			String queryString = "from Usuarios as model where mode"; 
			String varArgs="";
			int count=0;
			
			Query queryObject = getCurrentSession().createQuery(queryString);
			
			for (String propertyName : propertiesNames) {
			
				if(count<(propertiesNames.size()-1))
					varArgs+="model."+ propertyName + "= ? and ";
				else
					varArgs+="model."+ propertyName + "= ?";
					
				queryObject.setParameter(count, values.get(count++));
			
			}
			
			return queryObject.list();
			
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		
	}
	
	//Recuperar objetos de tipo Usuarios a partir de un das
	public List<Usuarios> findByDas(Object nombre) {
		return findByProperty(DAS, nombre);
	}
	
	//Recuperar objetos de tipo Usuarios a partir de un nombre
	public List<Usuarios> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}
	
	//Recuperar objetos de tipo Usuarios a partir de un apellido
	public List<Usuarios> findByApellido(Object descripcion) {
		return findByProperty(APELLIDO, descripcion);
	}

	//Recuperar objetos de tipo Usuarios a partir de un estado
	public List<Usuarios> findByEstado(Object descripcion) {
		return findByProperty(ESTADO, descripcion);
	}
	
	//Recuperar objetos de tipo Usuarios a partir de si ha iniciado sesion
	public List<Usuarios> findByInicio(Object descripcion) {
		return findByProperty(INICIO, descripcion);
	}
	
	//Recuperar objetos de tipo Usuarios a partir de un rol
	public List<Usuarios> findByRol(Object descripcion) {
		return findByProperty(CODIGO_ROL, descripcion);
	}

	//Recuperar todos los objetos de tipo Usuarios
	public List findAll() {
		log.debug("finding all Usuarios instances");
		try {
			String queryString = "from Usuarios";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	//Hace una consulta para actualizar un objeto de tipo Usuarios desde la base de datos a nuestra aplicación
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

	//Hace un update para actualizar un objeto de tipo Usuarios desde nuestra aplicación a la base de datos (e intenta bloquear)
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

	//Hace un update para actualizar un objeto de tipo Usuarios desde nuestra aplicación a la base de datos (sin intentar bloquear)
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
	
	//Devuelve un valor Usuarios de ApplicationContext
	public static Usuarios getFromApplicationContext(ApplicationContext ctx) {
		return (Usuarios) ctx.getBean("UsuariosDAO");
	}
}

package com.atos.hibernate.dto;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Roles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ROLES")
public class Roles implements java.io.Serializable {

	// Fields

	private Integer codigoRol;
	private String nombre_rol;
	private Set<Tareas> tareases = new HashSet<Tareas>(0);
	private Set<Usuarios> usuarioses = new HashSet<Usuarios>(0);

	// Constructors

	/** default constructor */
	public Roles() {
	}

	/** minimal constructor */
	public Roles(Integer codigoRol) {
		this.codigoRol = codigoRol;
	}

	/** full constructor */
	public Roles(Integer codigoRol, String descripcionRol, Set<Tareas> tareases,
			Set<Usuarios> usuarioses) {
		this.codigoRol = codigoRol;
		this.nombre_rol = descripcionRol;
		this.tareases = tareases;
		this.usuarioses = usuarioses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO_ROL", unique = true, nullable = false, precision = 2, scale = 0, length = 2)
	public Integer getCodigoRol() {
		return this.codigoRol;
	}

	public void setCodigoRol(Integer codigoRol) {
		this.codigoRol = codigoRol;
	}

	@Column(name = "NOMBRE_ROL", length = 100)
	public String getNombre_rol() {
		return nombre_rol;
	}

	public void setNombre_rol(String nombre_rol) {
		this.nombre_rol = nombre_rol;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roleses")
	public Set<Tareas> getTareases() {
		return this.tareases;
	}

	public void setTareases(Set<Tareas> tareases) {
		this.tareases = tareases;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	public Set<Usuarios> getUsuarioses() {
		return this.usuarioses;
	}

	public void setUsuarioses(Set<Usuarios> usuarioses) {
		this.usuarioses = usuarioses;
	}

}
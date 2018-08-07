package com.atos.hibernate.dto;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Tareas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TAREAS")
public class Tareas implements java.io.Serializable {

	// Fields

	private Byte id_Tarea;
	private String nombre_Tarea;
	private String descripcion_Tarea;
	private String estado;
	private Set<Roles> roleses = new HashSet<Roles>(0);

	// Constructors

	/** default constructor */
	public Tareas() {
	}

	/** minimal constructor */
	public Tareas(Byte id_Tarea) {
		this.id_Tarea = id_Tarea;
	}

	/** full constructor */
	public Tareas(Byte id_Tarea, String nombre_Tarea, String descripcion_Tarea, String estado,
			Set<Roles> roleses) {
		this.id_Tarea = id_Tarea;
		this.descripcion_Tarea = descripcion_Tarea;
		this.estado = estado;
		this.roleses = roleses;
	}

	// Property accessors
	@Id
	@Column(name = "ID_TAREA", unique = true, nullable = false, precision = 2, scale = 0, length = 5)
	public Byte getId_Tarea() {
		return id_Tarea;
	}

	public void setId_Tarea(Byte id_Tarea) {
		this.id_Tarea = id_Tarea;
	}
	
	@Column(name = "NOMBRE_TAREA", unique = true, nullable = false, precision = 2, scale = 0, length = 100)
	public String getNombre_Tarea() {
		return nombre_Tarea;
	}

	public void setNombre_Tarea(String nombre_Tarea) {
		this.nombre_Tarea = nombre_Tarea;
	}
	
	
	@Column(name = "DESCRIPCION_TAREA", length = 2000)
	public String getDescripcion_Tarea() {
		return descripcion_Tarea;
	}

	public void setDescripcion_Tarea(String descripcion_Tarea) {
		this.descripcion_Tarea = descripcion_Tarea;
	}
	
	
	
	@Column(name = "ESTADO_TAREA", length = 2000)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "ROLES_TAREAS", schema = "J172", joinColumns = { @JoinColumn(name = "ID_TAREA", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "CODIGO_ROL", nullable = false, updatable = false) })
	public Set<Roles> getRoleses() {
		return this.roleses;
	}

	public void setRoleses(Set<Roles> roleses) {
		this.roleses = roleses;
	}

}
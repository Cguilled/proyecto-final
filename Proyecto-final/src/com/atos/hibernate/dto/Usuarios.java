package com.atos.hibernate.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Usuarios entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "USUARIOS")
public class Usuarios implements java.io.Serializable {

	// Fields
	private String das;
	private String nombre;
	private String apellido;
	private String password;
	private String estado;
	private Roles roles;
	private String inicio;

	// Constructors

	/** default constructor */
	public Usuarios() {
	}

	/** minimal constructor */
	public Usuarios(String das) {
		this.das = das;
	}

	/** full constructor */
	public Usuarios(String nombreUsuario, String das, String apellido, String password,
			String estado, Roles roles, String inicio) {
		this.das = das;
		this.nombre = nombreUsuario;
		this.apellido = apellido;
		this.password = password;
		this.estado = estado;
		this.roles = roles;
		this.inicio = inicio;

	}

	// Property accessors
	@Id
	//@GeneratedValue(strategy = GenerationType.)
	@Column(name = "DAS", unique = true, nullable = false, length = 15)
	public String getDAS() {
		return das;
	}

	public void setDAS(String das) {
		this.das = das;
	}

	@Column(name = "NOMBRE", nullable = false, length = 20)
	public String getNombreUsuario() {
		return nombre;
	}

	public void setNombreUsuario(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "APELLIDO", nullable = false, length = 20)
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Column(name = "PASSWORD", nullable = false, length = 10)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "ESTADO", nullable = false, length = 10)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "INICIO", nullable = false, length = 10)
	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODIGO_ROL")
	public Roles getRoles() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

}
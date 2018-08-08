package com.atos.hibernate.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Usuarios entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "USUARIOS")
public class Usuarios implements java.io.Serializable {

	// Fields

	private Integer id_Usuario; 
	private String  das;
	private String  nombre;
	private String  apellido;
	private String  password;
	private String  estado;
	private Integer codigo_Rol;
	private boolean inicio;
	  //private Date fechaAlta;
     // private Date fechaBaja;
    //  private String carpetaDocumentacion;
   //   private String idioma;

	// Constructors

	/** default constructor */
	public Usuarios() {
	}

	/** minimal constructor */
	public Usuarios(Integer ID_Usuario) {
		this.id_Usuario = ID_Usuario;
	}

	/** full constructor */
	public Usuarios(Integer id_Usuario, String nombreUsuario, String das, String apellido, String password,String estado, Integer codigo_Rol, Boolean inicio) {
		this.id_Usuario=id_Usuario;
		this.das=das;
		this.nombre = nombreUsuario;
		this.apellido=apellido;
		this.password = password;
		this.estado = estado;
		this.codigo_Rol = codigo_Rol;
		this.inicio = inicio;
			
	}

	// Property accessors
	@Id
	@Column(name = "ID_Usuario", unique = true, nullable = false, length = 5)
	public Integer getidUsuario() {
		return this.id_Usuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombre=nombreUsuario;
	}

	@Column(name = "das", unique = true, nullable = false, length = 15)	
	public String getDAS() {
		return das;
	}

	public void setDAS(String das) {
		this.das = das;
	}

	
	@Column(name = "Nombre_Usuario", unique = true, nullable = false, length = 20)
	public String getNombreUsuario() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "Apellido", unique = true, nullable = false, length = 20)	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	@Column(name = "PASSWORD", length = 10)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "Estado", unique = true, nullable = false, length = 10)	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	@Column(name = "codigo_Rol", unique = true, nullable = false, length = 2)	
	public Integer getCodigo_Rol() {
		return codigo_Rol;
	}

	public void setCodigo_Rol(Integer codigo_Rol) {
		this.codigo_Rol = codigo_Rol;
	}

	@Column(name = "Inicio", unique = true, nullable = false, length = 10)	
	public boolean getInicio() {
		return inicio;
	}

	public void setInicio(boolean inicio) {
		this.inicio = inicio;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Codigo_Rol")
	public Integer getRoles() {
		return this.codigo_Rol;
	}

	public void setRoles(Integer Codigo_Rol) {
		this.codigo_Rol = Codigo_Rol;
	}

	public Integer getID_Usuario() {
		return id_Usuario;
	}

	public void setID_Usuario(Integer iD_Usuario) {
		this.id_Usuario = iD_Usuario;
	}

	public void setinicio(boolean inicio) {
		this.inicio = inicio;
		
	}

}
package com.atos.managedbean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.dao.DataAccessException;

import com.atos.hibernate.dto.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;

@ManagedBean(name="login")
@ViewScoped
public class Login_Bean {
	private Usuarios user;
	@ManagedProperty("#{gestion_usuario}")
	private IGestion_Usuarios gestion_usuario;
	private Integer username;
	private String password;
	
	@PostConstruct
	public void valores_Iniciales() {
		user = new Usuarios();

		username=0;
		password="";
	}
	
	public void login_check(ActionEvent event) throws DataAccessException{
		System.out.println("Realizando login...");
		try {
			user = gestion_usuario.consultar_PorClaveYDAS(username, password);
			if (user!=null) {
				//ir a la siguiente pantalla
			}
			
			else {
				//escribir mensaje de fallo
			}
		}catch (Exception e) {
			
		}
	}
	
	public IGestion_Usuarios getGestion_usuario() {
		return gestion_usuario;
	}
	public void setGestion_usuario(IGestion_Usuarios gestion_usuario) {
		this.gestion_usuario = gestion_usuario;
	}
	public Usuarios getUser() {
		return user;
	}
	public void setUser(Usuarios user) {
		this.user = user;
	}
	public Integer getUsername() {
		return username;
	}
	public void setUsername(Integer username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

package com.atos.managedbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.dao.DataAccessException;

import com.atos.hibernate.dto.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;

@ManagedBean(name="login_bean")
@ViewScoped
public class Login_Bean implements Serializable{
	private Usuarios user;
	private String das;
	private String password;
	
	@ManagedProperty("#{gestion_usuario}")
	private IGestion_Usuarios gestion_usuario;
	
	@PostConstruct
	public void valores_Iniciales() {
		user = new Usuarios();
		das="";
		password="";
	}
	
	public void login_check(ActionEvent event) throws DataAccessException{
		System.out.println("Realizando login...");
		try {
			user = gestion_usuario.consultar_PorClaveYDAS(das, password);
			if (user!=null) {
				//ir a la siguiente pantalla
			}
			
			else {
				//escribir mensaje de fallo
			}
		}catch (Exception e) {
			
		}
	}
	
	public String login_check2(){
		System.out.println("Realizando login...");
		try {
			user = gestion_usuario.consultar_PorClaveYDAS(das, password);
			if (user!=null) {
				return "true";
				//ir a la siguiente pantalla
			}
			
			else {
				//escribir mensaje de fallo
				return null;
			}
		}catch (Exception e) {
			
		}
		return null;
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
	public String getDas() {
		return das;
	}
	public void setDas(String das) {
		this.das = das;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

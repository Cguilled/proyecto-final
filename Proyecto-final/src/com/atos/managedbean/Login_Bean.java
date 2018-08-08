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
	private String das;
	private String password;
	
	@ManagedProperty("#{gestion_usuario}")
	private IGestion_Usuarios gestion_usuario;
	
	@PostConstruct
	public void valores_Iniciales() {
		das="";
		password="";
	}
	

	public String login_check(){

		System.out.println("Realizando login...");
		try {
			//comprueba si existe el usuario con la clave
			if (gestion_usuario.consultar_PorClaveYDAS(das, password)!=null) {
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

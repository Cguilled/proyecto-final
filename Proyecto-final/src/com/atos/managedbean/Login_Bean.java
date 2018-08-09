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
	private boolean visible;
	private String siguiente;
	
	@ManagedProperty("#{gestionUsuarios}")
	private IGestion_Usuarios gestionUsuarios;
	
	@PostConstruct
	public void valores_Iniciales() {
		das="";
		password="";
		visible=true;
	}
	

	public void login_check(ActionEvent evento){
		System.out.println("Realizando login...");
		try {
			//comprueba si existe el usuario con la clave
			if (gestionUsuarios.consultar_PorClaveYDAS(das, password)!=null) {
				//ir a la siguiente pantalla
			}
			
			else {
				//escribir mensaje de fallo
			}
		}catch (Exception e) {
			
		}
	}
	
	public String siguiente_pagina(){
		System.out.println("Redirigiendo a menuUser.xhtml");
		visible=false;
		siguiente="/xhtml/menuUser.xhtml";
		return "";
	}


	public IGestion_Usuarios getGestionUsuarios() {
		return gestionUsuarios;
	}

	public void setGestionUsuarios(IGestion_Usuarios gestionUsuarios) {
		this.gestionUsuarios = gestionUsuarios;
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

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(String siguiente_pagina) {
		this.siguiente = siguiente_pagina;
	}
}

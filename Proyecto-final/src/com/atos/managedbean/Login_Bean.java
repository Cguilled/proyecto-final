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
	private String siguiente_pagina;
	@ManagedProperty("#{gestion_usuario}")
	private IGestion_Usuarios gestion_usuario;
	
	@PostConstruct
	public void valores_Iniciales() {
		das="";
		password="";
		visible=true;
		siguiente_pagina="";
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
	
	public String siguiente(){
		System.out.println("Redirigiendo a menu.xhtml");
		visible=false;
		siguiente_pagina="/xhtml/menuUser.xhtml";
		return "";
	}
	
	public void listener(ActionEvent event) {}
	
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


	public boolean isVisible() {
		return visible;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}


	public String getSiguiente_pagina() {
		return siguiente_pagina;
	}


	public void setSiguiente_pagina(String siguiente_pagina) {
		this.siguiente_pagina = siguiente_pagina;
	}
}

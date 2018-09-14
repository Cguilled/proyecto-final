package com.atos.managedbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.dao.DataAccessException;
import org.springframework.web.context.annotation.SessionScope;

import com.atos.hibernate.dto.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;

@ManagedBean(name="login_bean")
@SessionScope
public class Login_Bean implements Serializable{
	private String das;
	private String password;
	private boolean visible;
	private String siguiente;
	private Usuarios recuperado;
	
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
			recuperado = gestionUsuarios.consultar_PorClaveYDAS(das, password);
			//comprueba si existe el usuario con la clave
			if (recuperado!=null) {
				//ir a la siguiente pantalla
				siguiente_pagina();
			}
			
			else {
				//escribir mensaje de fallo
				System.out.println("Fallo al realizar el login");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String siguiente_pagina(){
		visible = false;
		if(recuperado.isInicio()) {
			System.out.println("Redirigiendo a menuAdmin.xhtml");
			siguiente="/xhtml/menuAdmin.xhtml";
		}
		
		else {
			System.out.println("Redirigiendo a cambio de clave");
			siguiente="/xhtml/set_clave.xhtml";	
		}
		return "";
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
	
	public Usuarios getRecuperado() {
		return recuperado;
	}

	public void setRecuperado(Usuarios recuperado) {
		this.recuperado = recuperado;
	}

	
}

package com.atos.managedbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;

import org.springframework.web.context.annotation.SessionScope;

import com.atos.hibernate.dto.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;

@ManagedBean(name="password_bean")
@SessionScope
public class Password_Bean implements Serializable{
	private String password;
	private String confirmPassword;
	private boolean visible;
	private String siguiente;
	private String welcomeMessage;
	
	@ManagedProperty("#{gestionUsuarios}")
	private IGestion_Usuarios gestionUsuarios;
	
	@ManagedProperty("#{login_bean.recuperado}")
	private Usuarios recuperado;
	
	@ManagedProperty("#{mensajes_bean}")
	private Mensajes_Bean alerta;
	
	@PostConstruct
	public void valores_Iniciales() {
		password="";
		confirmPassword="";
		visible=true;
		welcomeMessage = "Bienvenido " + recuperado.getDas() + ", por favor, elija una contraseña nueva.";
	}

	public void pass_check(ActionEvent evento){
		System.out.println("Realizando cambio de clave...");
		
		try {
			//comprueba si existe el usuario con la clave
			if (password.equals(confirmPassword)) {
				//ir a la siguiente pantalla
				recuperado.setPassword(password);
				recuperado.setInicio(true);
				gestionUsuarios.modificacion_Usuario(recuperado);
				alerta.setMode(alerta.PASSWORD_CHANGED);
				alerta.listener(evento);
				siguiente_pagina();
			}
			
			else {
				//escribir mensaje de fallo
				alerta.setMode(alerta.PASSWORD_MISMATCH);
				alerta.listener(evento);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String siguiente_pagina(){
		System.out.println("Redirigiendo a menuAdmin.xhtml");
		visible=false;
		siguiente="/xhtml/menuAdmin.xhtml";
		return "";
	}

	public void setGestionUsuarios(IGestion_Usuarios gestionUsuarios) {
		this.gestionUsuarios = gestionUsuarios;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public String getWelcomeMessage() {
		return welcomeMessage;
	}
	
	public Mensajes_Bean getAlerta() {
		return alerta;
	}

	public void setAlerta(Mensajes_Bean alerta) {
		this.alerta = alerta;
	}
}
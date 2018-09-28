package com.atos.managedbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.atos.hibernate.dto.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;
import com.atos.springSecurity.CustomUrlAuthenticationSuccessHandler;

@ManagedBean(name = "password_bean")
@ViewScoped
public class Password_Bean implements Serializable {
	private String password;
	private String confirmPassword;
	private boolean visible;
	private String siguiente;
	private String welcomeMessage;
	private Usuarios usuario;
	Authentication auth;

	@ManagedProperty("#{gestionUsuarios}")
	private IGestion_Usuarios gestionUsuarios;

	/*@ManagedProperty("#{login_bean.recuperado}")
	private Usuarios recuperado;*/

	@ManagedProperty("#{mensajes_bean}")
	private Mensajes_Bean alerta;

	@Autowired
	private CustomUrlAuthenticationSuccessHandler authSuccessHandler;

	@PostConstruct
	public void valores_Iniciales() {
		// Autenticacion del objeto que se ha logueado
		auth = SecurityContextHolder.getContext().getAuthentication();
		// Objeto de que se ha logueado
		usuario = gestionUsuarios.consultar_PorDas(auth.getName());

		password = "";
		confirmPassword = "";
		visible = true;
		// welcomeMessage = "Bienvenido " + recuperado.getDas() + ", por favor, elija
		// una contraseña nueva.";
		welcomeMessage = "Bienvenido " + auth.getName() + ", escriba una contraseña nueva.";
	}

	public void pass_check(ActionEvent evento) {
		System.out.println("Realizando cambio de contraseña...");
		try {
			// comprueba si existe el usuario con la clave
			if (password.equals(confirmPassword)) {
				// ir a la siguiente pantalla
				// recuperado.setPassword(password);
				// recuperado.setInicio(true);
				//gestionUsuarios.modificacion_Usuario(recuperado);
				usuario.setPassword(password);
				usuario.setInicio(true);
				gestionUsuarios.modificacion_Usuario(usuario);
				alerta.setMode(alerta.PASSWORD_CHANGED);
				alerta.listener(evento);
				//authSuccessHandler.determineTargetUrl(auth);
				//siguiente_pagina();
				System.out.println("Contraseña modificada");
			} else {
				System.out.println("Las contrasenas no coinciden");
				// escribir mensaje de fallo
				alerta.setMode(alerta.PASSWORD_MISMATCH);
				//alerta.listener(evento);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String siguiente_pagina() {
		System.out.println("Redirigiendo a menuAdmin.xhtml");
		visible = false;
		siguiente = "/xhtml/menuAdmin.xhtml";
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

	/*public Usuarios getRecuperado() {
		return recuperado;
	}

	public void setRecuperado(Usuarios recuperado) {
		this.recuperado = recuperado;
	}*/

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
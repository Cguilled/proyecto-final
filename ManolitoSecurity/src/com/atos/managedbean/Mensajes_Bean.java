package com.atos.managedbean;

import java.io.Serializable;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.web.context.annotation.SessionScope;

@ManagedBean(name = "mensajes_bean")
@SessionScope
public class Mensajes_Bean implements Serializable {

	// Propiedades del bean
	private String autoHide = "true";
	private boolean closeAll = true;
	private int maxVisibleMessages = 0;
	private String position = "top-right";
	private String header = "Error de validación";

	
	// Getters y setters
	public String getAutoHide() {
		return autoHide;
	}

	public void setAutoHide(String autoHide) {
		this.autoHide = autoHide;
	}

	public boolean isCloseAll() {
		return closeAll;
	}

	public void setCloseAll(boolean closeAll) {
		this.closeAll = closeAll;
	}

	public int getMaxVisibleMessages() {
		return maxVisibleMessages;
	}

	public void setMaxVisibleMessages(int maxVisibleMessages) {
		this.maxVisibleMessages = maxVisibleMessages;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void listener(ActionEvent event) {
		//FacesContext facesContext = FacesContext.getCurrentInstance();

		// Eliminar mensajes existentes
//		Iterator<FacesMessage> i = facesContext.getMessages();
//		while (i.hasNext()) {
//			i.next();
//			i.remove();
//		}

		// Anadir mensajes
		String message = "Iniciando sesión...";
		FacesContext.getCurrentInstance().addMessage(message, new FacesMessage(message));
	}
}
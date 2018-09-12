package com.atos.managedbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.icefaces.ace.component.menuitem.MenuItem;
import org.icefaces.ace.model.DefaultMenuModel;
import org.springframework.web.context.annotation.SessionScope;

@ManagedBean(name = "menubar_bean")
@SessionScope
public class MenuBar_Bean implements Serializable{
	boolean visible_roles = false;
	boolean visible_usuarios = false;
	boolean visible_tareas = false;
	
	@PostConstruct
	public void valores_Iniciales() {
		System.out.println("Pasando por post construct, roles = " + visible_roles + ", usuarios = " + visible_usuarios + ", tareas = " + visible_tareas);
	}

	public void usuariosInterface(ActionEvent evento) {
		System.out.println("usuariosInterface");
		visible_usuarios = true;
		visible_tareas = false;
		visible_roles = false;
	}
	
	public void rolesInterface(ActionEvent evento) {
		System.out.println("rolesInterface");
		visible_usuarios = false;
		visible_tareas = false;
		visible_roles = true;	
	}
	
	public void tareasInterface(ActionEvent evento) {
		System.out.println("tareasInterface");
		visible_usuarios = false;
		visible_tareas = true;
		visible_roles = false;
	}
	
	public boolean isVisible_roles() {
		return visible_roles;
	}

	public boolean isVisible_usuarios() {
		return visible_usuarios;
	}

	public boolean isVisible_tareas() {
		return visible_tareas;
	}
}

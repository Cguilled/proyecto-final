package com.atos.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.icefaces.ace.component.menuitem.MenuItem;
import org.icefaces.ace.model.DefaultMenuModel;
import org.springframework.web.context.annotation.SessionScope;

@ManagedBean(name = "menubar_bean")
@SessionScope
public class MenuBar_Bean implements Serializable, ActionListener{
	public DefaultMenuModel modelo;
	MenuItem usuarios;
	MenuItem tareas;
	MenuItem roles;
	MenuItem consultar;
	MenuItem finSesion;
	
	public void buildMenuModel() {
	  	modelo = new DefaultMenuModel();
	  	usuarios = new MenuItem();
	  	tareas = new MenuItem();
	  	roles = new MenuItem();
	  	consultar = new MenuItem();
	  	finSesion = new MenuItem();
	  	
	  	usuarios.setId("itemUsuarios");
	  	usuarios.setValue("Usuarios");
	  	usuarios.addActionListener(this);
	  	
	  	tareas.setId("itemTareas");
	  	tareas.setValue("Tareas");
	  	tareas.addActionListener(this);
	  	
	  	roles.setId("itemRoles");
	  	roles.setValue("Roles");
	  	roles.addActionListener(this);
	  	
	  	consultar.setId("itemConsultar");
	  	consultar.setValue("Consultar");
	  	consultar.addActionListener(this);
	  	
	  	finSesion.setId("itemFinSesion");
	  	finSesion.setValue("FinSesion");
	  	finSesion.addActionListener(this);
	}

	@Override
	public void processAction(ActionEvent arg0) throws AbortProcessingException {
		// TODO Auto-generated method stub
		
	}
}

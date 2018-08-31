package com.atos.junittests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atos.hibernate.dto.Roles;
import com.atos.hibernate.dto.Tareas;
import com.atos.hibernate.modelo.IGestion_Tareas;
import com.atos.hibernate.modelo.IGestion_Usuarios;

public class Gestion_Tareas_Test {

	private Tareas test = new Tareas(new Integer(7), "tarea ejemplo", "descripcion de tarea", true, null);
	private IGestion_Tareas gestion;
	private ApplicationContext ctx;
	
	public Gestion_Tareas_Test() {
		ctx = new ClassPathXmlApplicationContext("com/atos/spring/modelo.xml");
	}
	
	@Before
	public void restartGestion() {
		gestion = ctx.getBean(IGestion_Tareas.class);
	}
	
	@Test
	public void testConsultar_PorCodigoTarea() {
		gestion.alta_Tarea(test);
		Tareas result = gestion.consultar_PorCodigoTarea(test);
		gestion.baja_Tarea(test);
		assertEquals(result.getId_Tarea(), test.getId_Tarea());
	}

	@Test
	public void testConsultar_Todos() {
		gestion.alta_Tarea(test);
		int size = gestion.consultar_Todos().size();
		gestion.baja_Tarea(test);
		assertEquals(size, 2);
	}

	@Test
	public void testModificacion_Tarea() {
		gestion.alta_Tarea(test);
		Tareas modified = test;
		modified.setDescripcion_Tarea("Esto es una descripcion");
		gestion.modificacion_Tarea(modified);
		Tareas result = gestion.consultar_PorCodigoTarea(modified);
		gestion.baja_Tarea(modified);
		
		assertEquals(result.getDescripcion_Tarea(), modified.getDescripcion_Tarea());
	}

}

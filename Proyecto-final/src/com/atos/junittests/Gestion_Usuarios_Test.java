package com.atos.junittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atos.hibernate.dto.Roles;
import com.atos.hibernate.dto.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;

public class Gestion_Usuarios_Test {
	private Usuarios defaultUser = new Usuarios();
	private IGestion_Usuarios gestion;
	private ApplicationContext ctx;
	
	public Gestion_Usuarios_Test() {
		ctx = new ClassPathXmlApplicationContext("com/atos/spring/modelo.xml");
	}
	
	@Before
	public void restartGestion() {
		gestion = ctx.getBean(IGestion_Usuarios.class);
	}

	@Test
	public void testConsultar_PorIdNombre() {
		Usuarios got;
		gestion.alta_Usuario(defaultUser);
		got = gestion.consultar_PorIdNombre(defaultUser.getId_Usuario());
		gestion.baja_Usuario(got);
		assertEquals(defaultUser.getDAS(), got.getDAS());
	}

	@Test
	public void testConsultar_Todos() {
		List<Usuarios> got;
		gestion.alta_Usuario(defaultUser);
		got = gestion.consultar_Todos();
		gestion.baja_Usuario(defaultUser);
		assertEquals(got, 1);
	}

	@Test
	public void testConsultar_PorClaveYDAS() {
		Usuarios got;
		gestion.alta_Usuario(defaultUser);
		gestion.baja_Usuario(defaultUser);
		got = gestion.consultar_PorClaveYDAS(defaultUser.getDAS(), defaultUser.getPassword());
		assertEquals(got.getDAS(), defaultUser.getDAS());
	}
	
	@Test
	public void testModificacion_Usuario() {
		Usuarios modifiedUser = defaultUser;
		modifiedUser.setPassword("22");
		gestion.alta_Usuario(defaultUser);
		gestion.modificacion_Usuario(modifiedUser);
		assertEquals(gestion.consultar_PorIdNombre(defaultUser.getId_Usuario()).getPassword(), modifiedUser.getPassword());
		gestion.baja_Usuario(defaultUser);
	}

}
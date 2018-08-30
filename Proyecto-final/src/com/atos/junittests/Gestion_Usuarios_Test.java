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
	private Usuarios defaultUser = new Usuarios("Javier", "A704755", "Gonzalez", "clem", "inactivo", new Roles((byte) 99), "true");
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
	public void testConsultar_PorDas() {
		System.out.println("id nombre");
		Usuarios got;
		gestion.alta_Usuario(defaultUser);
		got = gestion.consultar_PorDas(defaultUser.getDAS());
		gestion.baja_Usuario(got);
		assertEquals(defaultUser.getDAS(), got.getDAS());
	}

	@Test
	public void testConsultar_Todos() {
		System.out.println("todos");
		List<Usuarios> got;
		gestion.alta_Usuario(defaultUser);
		got = gestion.consultar_Todos();
		gestion.baja_Usuario(defaultUser);
		assertEquals(got.size(), 2);
		//2, 1 para un elemento estatico que SIEMPRE ha de estar en la tabla
		//como referencia y el otro para el ejemplo metido ya de antemano.
	}

	@Test
	public void testConsultar_PorClaveYDAS() {
		System.out.println("clave/DAS");
		Usuarios newInicial = new Usuarios("Javier", "A704753", "Gonzalez", "clem", "inactivo", new Roles((byte) 99), "true");
		Usuarios got;
		gestion.alta_Usuario(newInicial);
		got = gestion.consultar_PorClaveYDAS(newInicial.getDAS(), newInicial.getPassword());
		gestion.baja_Usuario(newInicial);
		assertEquals(got.getDAS(), newInicial.getDAS());
	}
	
	@Test
	public void testModificacion_Usuario() {
		System.out.println("modificacion_usuario");
		Usuarios modifiedUser = defaultUser;
		modifiedUser.setPassword("22");
		gestion.alta_Usuario(defaultUser);
		gestion.modificacion_Usuario(modifiedUser);
		assertEquals(gestion.consultar_PorDas(defaultUser.getDAS()).getPassword(), modifiedUser.getPassword());
		gestion.baja_Usuario(defaultUser);
	}

}
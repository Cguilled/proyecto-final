package com.atos.hibernate.modelo;

import java.util.List;

import com.atos.hibernate.dto.Usuarios;

public interface IGestion_Usuarios {

	void modificacion_Usuario(Usuarios usuario);

	void baja_Usuario(Usuarios usuario);

	void alta_Usuario(Usuarios usuario);

	List<Usuarios> consultar_Todos();

	Usuarios consultar_PorIdNombre(int id_usuario);

	Usuarios consultar_PorClaveYDAS(int das, String clave);

}
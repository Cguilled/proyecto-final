package com.atos.util;

import java.util.ArrayList;

public class Utilidades {
	// ArrayList que sera la contrasena final
	ArrayList<String> caracter = new ArrayList<String>();
	String palabro = "";

	public String randomPassword() {
		// Array con los caracteres que formaran la contrasena
		String[] caracteres = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
				"r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
				"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6",
				"7", "8", "9", "@", "#", "$", "$", "%", "&" };

		// Hasta 10 porque en la bbdd la contrasena tiene longitud 10
		for (int i = 0; i < 10; i++) {
			palabro += caracteres[(int) (Math.floor(Math.random() * caracteres.length))];
		}

		return palabro;
	}
}

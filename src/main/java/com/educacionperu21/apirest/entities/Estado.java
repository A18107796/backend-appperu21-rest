package com.educacionperu21.apirest.entities;

public enum Estado {

	ACTIVO("Activo"), INACTIVO("Inactivo"), PENDIENTE("Pendiente");

	Estado(String nombre) {
		this.nombre = nombre;
	}

	private final String nombre;

	public String getNombre() {
		return nombre;
	}
	
	
}

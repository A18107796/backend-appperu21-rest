package com.educacionperu21.apirest.enums;

public enum Estado {

	ACTIVO("Activo"), INACTIVO("Inactivo"), PENDIENTE("Pendiente"), ANULADO("Anulado");

	Estado(String nombre) {
		this.nombre = nombre;
	}

	private final String nombre;

	public String getNombre() {
		return nombre;
	}

}

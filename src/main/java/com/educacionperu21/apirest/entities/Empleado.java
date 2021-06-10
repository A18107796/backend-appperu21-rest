package com.educacionperu21.apirest.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "empleados")
public class Empleado extends Persona {

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "empleado")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "empleado", "password" })
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

package com.educacionperu21.apirest.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "estudiantes")
public class Estudiante extends Persona {
	
	@NotNull(message = "Usted debe seleccionar una sede")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sede")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Sede sede;

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}
	
	
}

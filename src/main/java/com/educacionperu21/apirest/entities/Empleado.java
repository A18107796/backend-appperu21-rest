package com.educacionperu21.apirest.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "empleados")
public class Empleado extends Persona {

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "empleado")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "empleado", "password" })
	private Usuario usuario;

	@NotNull(message = "Usted debe seleccionar un cargo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cargo")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Cargo cargo;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}


	@Override
	public String toString() {
		return super.toString() +  " Empleado{" +
				"usuario=" + usuario +
				", cargo=" + cargo +
				'}';
	}
}

package com.educacionperu21.apirest.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.educacionperu21.apirest.enums.Estado;


@Entity
@Table(name = "cursos")
public class Curso implements Serializable, IGenericStatusClass<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@NotEmpty(message = MessagesValidation.CAMPO_VACIO)
	private String nombre;

	@Enumerated(EnumType.STRING)
	private Estado estado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Curso)) {
			return false;
		}
		Curso a = (Curso) obj;

		return this.id != null && this.id.equals(a.getId());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

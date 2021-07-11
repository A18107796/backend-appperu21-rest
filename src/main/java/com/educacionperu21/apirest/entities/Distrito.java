package com.educacionperu21.apirest.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "distritos")
public class Distrito extends GenericEntityAbstract {

	@Id
	@Column(name = "id_distrito")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nombre_distrito")
	private String nombre;

	@NotNull(message = "Usted debe seleccionar un departamento")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_provincia")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Provincia provincia;

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

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

}

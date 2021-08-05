package com.educacionperu21.apirest.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.educacionperu21.apirest.enums.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "especializaciones")
public class Especializacion implements Serializable, IGenericStatusClass<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = MessagesValidation.CAMPO_VACIO)
	private String nombre;

	@NotNull(message = "Usted debe seleccionar un tipo de especializacion")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTipoEspecializacion")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Especializacion_Tipo tipo_especializacion;

	@Enumerated(EnumType.STRING)
	private Estado estado;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Curso> cursos;

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

	public Especializacion_Tipo getTipo_especializacion() {
		return tipo_especializacion;
	}

	public void setTipo_especializacion(Especializacion_Tipo tipo_especializacion) {
		this.tipo_especializacion = tipo_especializacion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void addCurso(Curso curso) {
		this.cursos.add(curso);
	}

	public void removeCurso(Curso curso) {
		this.cursos.remove(curso);
	}
	
	public void clearCursos() {
		this.cursos.clear();
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	

}

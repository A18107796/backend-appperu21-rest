package com.educacionperu21.apirest.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.educacionperu21.apirest.enums.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "matriculas")
public class Matricula extends GenericEntityAbstract<Integer> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer num_cuotas;

	private String detalles;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_reg;

	@NotEmpty(message = MessagesValidation.CAMPO_VACIO)
	private String turno;

	@Enumerated(EnumType.STRING)
	private Estado estado;

	@NotNull(message = "Usted debe seleccionar un Empleado")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empleado")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Empleado empleado;

	@NotNull(message = "Usted debe seleccionar un Estudiante")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_especializacion")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Especializacion especializacion;

	@NotNull(message = "Usted debe seleccionar un Estudiante")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estudiante")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Estudiante estudiante;

	@NotNull(message = "Usted debe seleccionar un Periodo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_periodo")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Periodo periodo;

	@NotNull(message = "Usted debe seleccionar una Sede")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sede")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Sede sede;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "matricula", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = { "matricula", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private List<Matricula_Pagos> pagos;

	public Matricula() {
		this.pagos = new ArrayList<Matricula_Pagos>();
	}

	public List<Matricula_Pagos> getPagos() {
		return pagos;
	}

	public void setPagos(List<Matricula_Pagos> pagos) {
		this.pagos = pagos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNum_cuotas() {
		return num_cuotas;
	}

	public void setNum_cuotas(Integer num_cuotas) {
		this.num_cuotas = num_cuotas;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public Date getFecha_reg() {
		return fecha_reg;
	}

	public void setFecha_reg(Date fecha_reg) {
		this.fecha_reg = fecha_reg;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Especializacion getEspecializacion() {
		return especializacion;
	}

	public void setEspecializacion(Especializacion especializacion) {
		this.especializacion = especializacion;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

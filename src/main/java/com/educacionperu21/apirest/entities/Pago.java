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
import javax.validation.constraints.NotNull;

import com.educacionperu21.apirest.enums.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pagos")
public class Pago implements Serializable, IGenericStatusClass<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String npago;

	private String ruc;

	@NotNull(message = "Usted debe seleccionar un Comprobante")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_comprobante")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Tipo_Comprobante tipo_comprobante;

	@NotNull(message = "Usted debe seleccionar un Tipo de Pago")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_pago")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Tipo_Pago tipo_pago;

	@NotNull(message = "Usted debe seleccionar un Empleado")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empleado")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Empleado empleado;

	@NotNull(message = "Usted debe seleccionar un Estudiante")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estudiante")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Estudiante estudiante;

	@NotNull(message = "Usted debe seleccionar una Moneda")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_moneda")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Moneda moneda;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Enumerated(EnumType.STRING)
	private Estado estado;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pago")
	@JsonIgnoreProperties({ "pagos", "hibernateLazyInitializer", "handler" })
	private List<PagoDetalles> pagoDetalles;

	public Pago() {
		this.pagoDetalles = new ArrayList<PagoDetalles>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNpago() {
		return npago;
	}

	public void setNpago(String npago) {
		this.npago = npago;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public Tipo_Comprobante getTipo_comprobante() {
		return tipo_comprobante;
	}

	public void setTipo_comprobante(Tipo_Comprobante tipo_comprobante) {
		this.tipo_comprobante = tipo_comprobante;
	}

	public Tipo_Pago getTipo_pago() {
		return tipo_pago;
	}

	public void setTipo_pago(Tipo_Pago tipo_pago) {
		this.tipo_pago = tipo_pago;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

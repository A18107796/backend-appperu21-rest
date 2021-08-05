package com.educacionperu21.apirest.entities;

import java.io.Serializable;

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
@Table(name = "pagos_detalles")
public class PagoDetalles implements Serializable, GenericEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private int cantidad;

	private double subtotal;

	@NotNull(message = "Usted debe seleccionar un Pago")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_matricula_pago")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Matricula_Pagos pago;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public Matricula_Pagos getPago() {
		return pago;
	}

	public void setPago(Matricula_Pagos pago) {
		this.pago = pago;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

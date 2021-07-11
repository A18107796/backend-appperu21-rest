package com.educacionperu21.apirest.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Pension")
public class Pension extends GenericEntityAbstract implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String cod;

	private String descripcion;

	private Double monto;

	public Pension() {
		
	}
	
	
	
	
	public Pension(String cod, String descripcion, Double monto) {
		this.cod = cod;
		this.descripcion = descripcion;
		this.monto = monto;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

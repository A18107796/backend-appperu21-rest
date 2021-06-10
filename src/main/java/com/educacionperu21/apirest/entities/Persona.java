package com.educacionperu21.apirest.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
public abstract class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = MessagesValidation.CAMPO_VACIO)
	@Column(nullable = false)
	private String nombres;

	@NotEmpty(message = MessagesValidation.CAMPO_VACIO)
	@Column(nullable = false)
	private String apellidos;

	@NotEmpty(message = MessagesValidation.CAMPO_VACIO)
	@Size(min = 8, max = 12, message = "El campo minimo es 8")
	@Column(nullable = false, unique = true, length = 12)
	private String num_doc;

	@NotNull(message = "Seleccione su estado civil")
	private Character estado_civil;

	@NotNull(message = "Seleccione su genero")
	private Character genero;

	@NotNull(message = "Seleccione su fecha de nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fecha_nac;

	@NotNull(message = "Usted debe seleccionar un distrito")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idDistrito")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Distrito distrito;

	@NotEmpty(message = "El campo esta vacio")
	@Email(message = "El email no tiene un formato correcto.")
	@Size(min = 0, message = "El campo minimo es 0")
	@Column(nullable = false, unique = true)
	private String email;

	@NotNull(message = "Usted debe seleccionar un tipo de documento")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTipoDocumento")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Tipo_Documento tipo_documento;

	@NotNull(message = "Ingresé dirección")
	private String direccion;

	@CreatedDate
	private Date fecha_creación;

	@LastModifiedDate
	private Date fecha_modificacion;

	@Null
	private Date fecha_inactivo;

	@Enumerated(EnumType.STRING)
	private Estado estado;

	@PrePersist
	public void generateDates() {
		estado = Estado.PENDIENTE;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNum_doc() {
		return num_doc;
	}

	public void setNum_doc(String num_doc) {
		this.num_doc = num_doc;
	}

	public Character getEstado_civil() {
		return estado_civil;
	}

	public void setEstado_civil(Character estado_civil) {
		this.estado_civil = estado_civil;
	}

	public Character getGenero() {
		return genero;
	}

	public void setGenero(Character genero) {
		this.genero = genero;
	}

	public Tipo_Documento getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(Tipo_Documento tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	public Date getFecha_creación() {
		return fecha_creación;
	}

	public void setFecha_creación(Date fecha_creación) {
		this.fecha_creación = fecha_creación;
	}

	public Date getFecha_modificacion() {
		return fecha_modificacion;
	}

	public void setFecha_modificacion(Date fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}

	public Date getFecha_inactivo() {
		return fecha_inactivo;
	}

	public void setFecha_inactivo(Date fecha_inactivo) {
		this.fecha_inactivo = fecha_inactivo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Date getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}

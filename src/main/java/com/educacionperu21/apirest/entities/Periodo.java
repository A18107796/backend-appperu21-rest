package com.educacionperu21.apirest.entities;

import com.educacionperu21.apirest.enums.Estado;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "periodos")
public class Periodo implements Serializable, IGenericStatusClass<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = MessagesValidation.CAMPO_VACIO)
    @Column(nullable = false)
    private String nombre;

    @NotNull(message = MessagesValidation.CAMPO_VACIO)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha_inicio;

    @Temporal(TemporalType.DATE)
    private Date fecha_fin;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @NotNull(message = MessagesValidation.CAMPO_VACIO)
    @Column(nullable = false, unique = false)
    private int anio;

    public Periodo() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

}

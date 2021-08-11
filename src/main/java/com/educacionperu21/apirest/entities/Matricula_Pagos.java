package com.educacionperu21.apirest.entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Null;

import com.educacionperu21.apirest.enums.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "matricula_pagos")
public class Matricula_Pagos implements Serializable, IGenericStatusClass<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Temporal(TemporalType.DATE)
    private Date fecha_pago;

    @Temporal(TemporalType.DATE)
    private Date fecha_venc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pension")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Pension pension;


    private double mora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_matricula")
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
    private Matricula matricula;

    public Pension getPension() {
        return pension;
    }

    public void setPension(Pension pension) {
        this.pension = pension;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public Date getFecha_venc() {
        return fecha_venc;
    }

    public void setFecha_venc(Date fecha_venc) {
        this.fecha_venc = fecha_venc;
    }


    public double getMora() {
        return mora;
    }

    public void setMora(double mora) {
        this.mora = mora;
    }

    public long getDiasVencido() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        TimeUnit time = TimeUnit.DAYS;
        Date fecha = new Date();
        Date hoy = new Date();
        long daysDiference = 0;
        try {
            hoy = sdf.parse(sdf.format(hoy));
            fecha = sdf.parse(this.fecha_venc.toString());
            long diff = hoy.getTime() - fecha.getTime();
            daysDiference = time.convert(diff, TimeUnit.MILLISECONDS);

            if (daysDiference <= 0) {
                daysDiference = 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return daysDiference;
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

}

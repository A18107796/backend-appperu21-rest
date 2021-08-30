package com.educacionperu21.apirest.entities;

import java.io.Serializable;

import javax.persistence.*;
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
    @JsonIgnoreProperties({"pago.matricula", "hibernateLazyInitializer", "handler"})
    private Matricula_Pagos pago;

    private double cargos;

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

    public double getCargos() {
        return cargos;
    }

    public void setCargos(double cargos) {
        this.cargos = cargos;
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

}

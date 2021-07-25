package com.educacionperu21.apirest.entities;


import com.educacionperu21.apirest.enums.Estado;

import javax.persistence.*;

@MappedSuperclass
public abstract class GenericEntityAbstractStatus<Key> extends GenericEntityAbstract<Key> implements IGenericStatusClass {

    @Enumerated(EnumType.STRING)
    protected Estado estado;


    @PrePersist
    public void generateDates() {
        estado = Estado.PENDIENTE;
    }


    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}

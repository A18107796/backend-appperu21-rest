package com.educacionperu21.apirest.entities;

import com.educacionperu21.apirest.enums.Estado;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


public interface IGenericStatusClass<Key>{

    Estado getEstado();

    void setEstado(Estado estado);

    Key getId();

    void setId(Key id);
}

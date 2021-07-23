package com.educacionperu21.apirest.entities;

import com.educacionperu21.apirest.enums.Estado;

public interface IGenericStatusClass {

    Estado getEstado();

    void setEstado(Estado estado);
}

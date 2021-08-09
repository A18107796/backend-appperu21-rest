package com.educacionperu21.apirest.exceptions;

public class SQLIntegrityException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    public SQLIntegrityException() {
        super("No puede eliminar ni actualizar objetos que tengan relacion con otros datos.");
    }
}

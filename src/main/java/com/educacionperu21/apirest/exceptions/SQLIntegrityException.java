package com.educacionperu21.apirest.exceptions;

public class SQLIntegrityConstraintViolationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final String DESCRIPTION = "Not Found Exception (404)";

    public SQLIntegrityConstraintViolationException() {
        super("No puede eliminar ni actualizar objetos que tengan relacion con otros datos.");
    }
}

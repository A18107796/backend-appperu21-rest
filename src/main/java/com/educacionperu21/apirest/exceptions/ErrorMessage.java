package com.educacionperu21.apirest.exceptions;

import java.net.ConnectException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

public class ErrorMessage {

	private String exception;

	private String message;

	private String path;

	private List<?> errors;

	public ErrorMessage(Exception exception, String path) {

		this.exception = exception.getClass().getSimpleName();
		this.message = exception.getMessage();
		if (exception instanceof ConnectException) {
			this.message = "La base de datos no se encuentra disponible";
		}

		if(exception instanceof SQLIntegrityConstraintViolationException){
			this.message = "No puede actualizar ni eliminar registros que se encuentren vinculados a otros datos.";
		}

		this.path = path;
	}

	public ErrorMessage(Exception exception, String path, List<?> errors2) {
		this.exception = exception.getClass().getSimpleName();
		this.message = exception.getMessage();
		this.path = path;
		this.errors = errors2;
	}

	public List<?> getErrors() {
		return errors;
	}

	public void setErrors(List<?> errors2) {
		this.errors = errors2;
	}

	public String getException() {
		return exception;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		return "ErrorMessage [exception=" + exception + ", message=" + message + ", path=" + path + "]";
	}

}

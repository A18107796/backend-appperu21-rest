package com.educacionperu21.apirest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educacionperu21.apirest.entities.Empleado;
import com.educacionperu21.apirest.exceptions.InternalServerError;
import com.educacionperu21.apirest.generics.controller.GenericController;
import com.educacionperu21.apirest.services.IEmpleadoService;

@RestController
@RequestMapping(path = ControllerPaths.PATH_EMPLEADO)
public class EmpleadoRestController extends GenericController<Empleado, Integer, IEmpleadoService> {

	public EmpleadoRestController() {
		this.type = Empleado.class;
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<?> checkEmail(@PathVariable(name = "email") String email) {
		Map<String, Boolean> response;
		boolean existsEmail;
		try {
			existsEmail = service.emailExists(email);
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
		response = new HashMap<>();
		response.put("existe", existsEmail);
		return ResponseEntity.ok(response);
	}
}

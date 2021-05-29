package com.educacionperu21.apirest.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educacionperu21.apirest.entities.Empleado;
import com.educacionperu21.apirest.generics.controller.GenericController;
import com.educacionperu21.apirest.services.IEmpleadoService;

@RestController
@RequestMapping(path = PathsController.PATH_EMPLEADO)
public class EmpleadoRestController extends GenericController<Empleado, Integer, IEmpleadoService> {

	public EmpleadoRestController() {
		this.type = Empleado.class;
	}
}

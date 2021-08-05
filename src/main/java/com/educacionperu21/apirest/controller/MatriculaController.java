package com.educacionperu21.apirest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.educacionperu21.apirest.generics.controller.GenericControllerWithStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educacionperu21.apirest.entities.Matricula;
import com.educacionperu21.apirest.exceptions.BadRequestException;
import com.educacionperu21.apirest.generics.controller.GenericController;
import com.educacionperu21.apirest.services.IMatriculaService;

@CrossOrigin(origins = ControllerPaths.PATH_ANGULAR_ORIGIN)
@RestController
@RequestMapping(path = ControllerPaths.PATH_MATRICULAS)
public class MatriculaController extends GenericControllerWithStatus<Matricula, Integer, IMatriculaService> {

	public MatriculaController() {
		this.type = Matricula.class;
	}

	@Override
	@PostMapping("/matricular")
	public ResponseEntity<?> crear(@Valid @RequestBody Matricula entity, BindingResult result) {
		Matricula entityCreated;
		Map<String, Object> response = new HashMap<>();

		System.out.println("FUERRA ERROR");
		if (result.hasErrors()) {
			System.out.println("ERROR");
			List<String> errors2 = new ArrayList<String>();
			errors2 = result.getFieldErrors().stream()
					.map(err -> "'" + err.getField() + "': " + err.getDefaultMessage()).collect(Collectors.toList());
			response.put("errors", errors2);
			throw new BadRequestException(errors2);

		}
		entityCreated = service.save(entity);

		return ResponseEntity.status(HttpStatus.CREATED).body(entityCreated);
	}
}

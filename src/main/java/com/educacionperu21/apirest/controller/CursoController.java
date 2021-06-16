package com.educacionperu21.apirest.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educacionperu21.apirest.entities.Curso;
import com.educacionperu21.apirest.entities.Especializacion;
import com.educacionperu21.apirest.exceptions.BadRequestException;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import com.educacionperu21.apirest.generics.controller.GenericController;
import com.educacionperu21.apirest.services.ICursoService;

@RestController
@RequestMapping(path = ControllerPaths.PATH_CURSOS)
public class CursoController extends GenericController<Curso, Integer, ICursoService> {

	public CursoController() {
		this.type = Curso.class;
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Curso curso, BindingResult result,
			@PathVariable("id") Integer id_curso) {
		Map<String, Object> response = new HashMap<>();

		Optional<Curso> o = service.findById(id_curso);
		if (!o.isPresent()) {
			throw new NotFoundException("El curso no existe");
		}

		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<String, String>();
			errors = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
			response.put("errors", errors);
			throw new BadRequestException(errors);
		}

		Curso cursoDB = o.get();
		cursoDB.setEstado(curso.getEstado());
		cursoDB.setNombre(curso.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));

	}
}

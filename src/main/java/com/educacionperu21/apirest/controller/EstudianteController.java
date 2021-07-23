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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educacionperu21.apirest.entities.Empleado;
import com.educacionperu21.apirest.entities.Estudiante;
import com.educacionperu21.apirest.exceptions.BadRequestException;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import com.educacionperu21.apirest.generics.controller.GenericController;
import com.educacionperu21.apirest.services.IEstudianteService;

@CrossOrigin(origins = ControllerPaths.PATH_ANGULAR_ORIGIN)
@RestController
@RequestMapping(path = ControllerPaths.PATH_ESTUDIANTES)
public class EstudianteController extends GenericController<Estudiante, Integer, IEstudianteService>{
	
	public EstudianteController() {
		this.type = Estudiante.class;
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Estudiante estudiante, BindingResult result,
			@PathVariable("id") Integer id_estudiante) {
		Map<String, Object> response = new HashMap<>();

		Optional<Estudiante> o = service.findById(id_estudiante);

		if (!o.isPresent()) {
			throw new NotFoundException("El estudiante no existe.");
		}

		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<String, String>();
			errors = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
			response.put("errors", errors);
			throw new BadRequestException(errors);
		}

		Estudiante estudianteDB = o.get();
		estudianteDB.setNombres(estudiante.getNombres());
		estudianteDB.setApellidos(estudiante.getApellidos());
		estudianteDB.setNum_doc(estudiante.getNum_doc());
		estudianteDB.setEstado_civil(estudiante.getEstado_civil());
		estudianteDB.setGenero(estudiante.getGenero());
		estudianteDB.setFecha_nac(estudiante.getFecha_nac());
		estudianteDB.setDistrito(estudiante.getDistrito());
		estudianteDB.setDireccion(estudiante.getDireccion());
		estudianteDB.setTipo_documento(estudiante.getTipo_documento());
		estudianteDB.setEmail(estudiante.getEmail());
		estudianteDB.setTelefono(estudiante.getTelefono());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(estudianteDB));

	}
}

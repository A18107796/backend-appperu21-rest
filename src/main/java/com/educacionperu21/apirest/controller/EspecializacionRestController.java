package com.educacionperu21.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educacionperu21.apirest.entities.Curso;
import com.educacionperu21.apirest.entities.Especializacion;
import com.educacionperu21.apirest.exceptions.BadRequestException;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import com.educacionperu21.apirest.exceptions.ServiceUnavailableException;
import com.educacionperu21.apirest.generics.controller.GenericController;
import com.educacionperu21.apirest.services.IEspecializacionService;
import com.educacionperu21.apirest.services.IEspecializacionTipoService;

@CrossOrigin(origins = ControllerPaths.PATH_ANGULAR_ORIGIN)
@RestController
@RequestMapping(path = ControllerPaths.PATH_ESPECIALIZACION)
public class EspecializacionRestController
		extends GenericController<Especializacion, Integer, IEspecializacionService> {

	@Autowired
	private IEspecializacionTipoService esp_tipo_service;

	public EspecializacionRestController() {
		this.type = Especializacion.class;
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Especializacion especializacion, BindingResult result,
			@PathVariable("id") Integer id_especializacion) {
		Map<String, Object> response = new HashMap<>();

		try {
			Optional<Especializacion> o = service.findById(id_especializacion);

			if (!o.isPresent()) {
				throw new NotFoundException("La especializacion no existe");
			}

			if (result.hasErrors()) {
				Map<String, String> errors = new HashMap<String, String>();
				errors = result.getFieldErrors().stream().collect(
						Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
				response.put("errors", errors);
				throw new BadRequestException(errors);
			}

			Especializacion especializacionDB = o.get();
			especializacionDB.setEstado(especializacion.getEstado());
			especializacionDB.setNombre(especializacion.getNombre());
			especializacionDB.setTipo_especializacion(especializacion.getTipo_especializacion());
			return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(especializacionDB));
			
		} catch (DataAccessException e) {
			throw new ServiceUnavailableException(e.getMessage());
		}
	}

	@GetMapping("/tipos")
	private ResponseEntity<?> listar() {
		return ResponseEntity.ok().body(esp_tipo_service.findAll());
	}

	@PutMapping("/{id}/save-changes")
	private ResponseEntity<?> saveChanges(@RequestBody List<Curso> cursos,
			@PathVariable("id") Integer id_especializacion) {
		Optional<Especializacion> o = this.service.findById(id_especializacion);
		if (o.isEmpty()) {
			throw new NotFoundException("La/el " + getClassName() + " no existe.");
		}
		Especializacion espBD = o.get();
		espBD.clearCursos();
		cursos.forEach(espBD::addCurso);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.update(espBD));
	}

}

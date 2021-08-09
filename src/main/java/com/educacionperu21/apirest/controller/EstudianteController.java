package com.educacionperu21.apirest.controller;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.educacionperu21.apirest.exceptions.InternalServerError;
import com.educacionperu21.apirest.generics.controller.GenericControllerWithStatus;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.educacionperu21.apirest.entities.Empleado;
import com.educacionperu21.apirest.entities.Estudiante;
import com.educacionperu21.apirest.exceptions.BadRequestException;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import com.educacionperu21.apirest.generics.controller.GenericController;
import com.educacionperu21.apirest.services.IEstudianteService;

@CrossOrigin(origins = ControllerPaths.PATH_ANGULAR_ORIGIN)
@RestController
@RequestMapping(path = ControllerPaths.PATH_ESTUDIANTES)
public class EstudianteController extends GenericControllerWithStatus<Estudiante, Integer, IEstudianteService> {

    public EstudianteController() {
        this.type = Estudiante.class;
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> checkEmail(@PathVariable(name = "email") String email) {
        Map<String, Boolean> response;
        boolean existsEmail;
        try {
            existsEmail = service.emailsExists(email);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
        response = new HashMap<>();
        response.put("existe", existsEmail);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/buscar/dni/{dni}")
    public ResponseEntity<?> buscarEstudianteXDNI(@PathVariable(name = "dni") String dni) {
        if(dni == null || dni.length() <= 0){
            throw new BadRequestException("Envie DNI valido");
        }
        return ResponseEntity.ok(service.findStudentByDNI(dni).get());
    }


    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> checkDNI(@PathVariable(name = "dni") String dni) {
        Map<String, Boolean> response;
        boolean existsEmail;
        try {
            existsEmail = service.dniExists(dni);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
        response = new HashMap<>();
        response.put("existe", existsEmail);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Estudiante estudiante, BindingResult result,
                                    @PathVariable("id") Integer id_estudiante) {
        Map<String, Object> response = new HashMap<>();

        Optional<Estudiante> o = service.findById(id_estudiante);

        if (o.isEmpty()) {
            throw new NotFoundException("El estudiante no existe.");
        }

        if (result.hasErrors()) {
            List<String> errors = new ArrayList<String>();
            errors = result.getFieldErrors().stream().map(err -> "'" + err.getField() + "': " + err.getDefaultMessage())
                    .collect(Collectors.toList());
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
        estudianteDB.setSede(estudiante.getSede());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(estudianteDB));

    }
}

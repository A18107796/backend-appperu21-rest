package com.educacionperu21.apirest.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.educacionperu21.apirest.generics.controller.GenericControllerWithStatus;
import com.educacionperu21.apirest.services.ICargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educacionperu21.apirest.entities.Empleado;
import com.educacionperu21.apirest.entities.Especializacion;
import com.educacionperu21.apirest.exceptions.BadRequestException;
import com.educacionperu21.apirest.exceptions.InternalServerError;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import com.educacionperu21.apirest.exceptions.ServiceUnavailableException;
import com.educacionperu21.apirest.generics.controller.GenericController;
import com.educacionperu21.apirest.services.IEmpleadoService;

@RestController
@RequestMapping(path = ControllerPaths.PATH_EMPLEADO)
public class EmpleadoRestController extends GenericControllerWithStatus<Empleado, Integer, IEmpleadoService> {

    @Autowired
    private ICargoService cargoService;

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



    @GetMapping("/cargos")
    public ResponseEntity<?> getCargos() {
        return ResponseEntity.ok().body(cargoService.findAll());
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
    public ResponseEntity<?> update(@Valid @RequestBody Empleado empleado, BindingResult result,
                                    @PathVariable("id") Integer id_empleado) {
        Map<String, Object> response = new HashMap<>();

        Optional<Empleado> o = service.findById(id_empleado);

        if (!o.isPresent()) {
            throw new NotFoundException("El empleado no existe.");
        }

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
            response.put("errors", errors);
            throw new BadRequestException(errors);
        }

        Empleado empleadoDB = o.get();
        empleadoDB.setNombres(empleado.getNombres());
        empleadoDB.setApellidos(empleado.getApellidos());
        empleadoDB.setNum_doc(empleado.getNum_doc());
        empleadoDB.setEstado_civil(empleado.getEstado_civil());
        empleadoDB.setGenero(empleado.getGenero());
        empleadoDB.setFecha_nac(empleado.getFecha_nac());
        empleadoDB.setDistrito(empleado.getDistrito());
        empleadoDB.setDireccion(empleado.getDireccion());
        empleadoDB.setCargo(empleado.getCargo());
        empleadoDB.setTipo_documento(empleado.getTipo_documento());
        empleadoDB.setEmail(empleado.getEmail());
        empleadoDB.setTelefono(empleado.getTelefono());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.update(empleadoDB));

    }
}

package com.educacionperu21.apirest.generics.controller;

import com.educacionperu21.apirest.entities.GenericEntity;
import com.educacionperu21.apirest.entities.IGenericStatusClass;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.exceptions.BadRequestException;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

public class GenericControllerWithStatus<Entity extends IGenericStatusClass<Key>, Key, Service extends GenericServiceWithStatus<Entity, Key>>{


    @Autowired
    protected Service service;

    protected Class<Entity> type;

    @GetMapping
    private ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/pagina")
    private ResponseEntity<?> listar(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> buscarPorID(@PathVariable("id") Key id) {
        Optional<Entity> o = service.findById(id);
        Map<String, Object> resp = new HashMap<String, Object>();
        if (o.isEmpty()) {
            resp.put("error", "El ID no existe");
            throw new NotFoundException("El " + getClassName() + " no existe");
        }

        resp.put("mensaje:", getClassName() + " encontrado");
        resp.put(getClassName(), o.get());
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Entity entity, BindingResult result) {
        Entity entityCreated;
        //entity.setId(null);
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = new ArrayList<String>();
            errors = result.getFieldErrors().stream().map(err -> "'" + err.getField() + "': " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            /*
             * Map<String, String> errors = new HashMap<String, String>(); errors =
             * result.getFieldErrors().stream()
             * .collect(Collectors.toMap(FieldError::getField,
             * DefaultMessageSourceResolvable::getDefaultMessage)); response.put("errors",
             * errors);
             */
            throw new BadRequestException(errors);
        }
        entityCreated = service.save(entity);

        return ResponseEntity.status(HttpStatus.CREATED).body(entityCreated);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> eliminar(@PathVariable Key id) {

        Optional<Entity> o = service.findById(id);

        if (o.isEmpty()) {
            throw new NotFoundException("El/La " + getClassName() + " no existe");
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    protected String getClassName() {
        return type.getSimpleName().toLowerCase();
    }

    @PutMapping("/estado/{id}" )
    private ResponseEntity<?> changeStatus(@PathVariable("id") Key id, @RequestParam(name = "estado") Estado estado) {
        service.ChangeStatus(id, estado);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/" )
    private ResponseEntity<?> findAllByStatus(@RequestParam(name = "estado") Estado estado) {

        return ResponseEntity.ok().body(service.findAllByStatus(estado));
    }

}

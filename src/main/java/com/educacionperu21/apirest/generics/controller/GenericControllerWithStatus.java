package com.educacionperu21.apirest.generics.controller;

import com.educacionperu21.apirest.entities.GenericEntityAbstractStatus;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class GenericControllerWithStatus<T extends GenericEntityAbstractStatus<Key>, Key, Service extends GenericServiceWithStatus<T, Key>>
        extends GenericController<T, Key, Service> {

    @PutMapping("/estado/{id}" )
    private ResponseEntity<?> changeStatus(@PathVariable("id") Key id, @RequestParam(name = "estado") Estado estado) {
        service.ChangeStatus(id, estado);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/" )
    private ResponseEntity<?> changeStatus(@RequestParam(name = "estado") Estado estado) {

        return ResponseEntity.ok().body(service.findAllByStatus(estado));
    }

}

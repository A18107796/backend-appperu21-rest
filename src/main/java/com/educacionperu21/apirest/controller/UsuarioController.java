package com.educacionperu21.apirest.controller;

import com.educacionperu21.apirest.entities.Rol;
import com.educacionperu21.apirest.entities.Usuario;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import com.educacionperu21.apirest.generics.controller.GenericController;
import com.educacionperu21.apirest.services.IUsuarioService;
import com.educacionperu21.apirest.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = ControllerPaths.PATH_ANGULAR_ORIGIN)
@RestController
@RequestMapping(path = ControllerPaths.PATH_USUARIO)
public class UsuarioController extends GenericController<Usuario, Integer, IUsuarioService> {

    public UsuarioController() {
        this.type = Usuario.class;
    }

    @Autowired
    private RolService rolService;

    @PutMapping("/status/{id}/{boolean}")
    public ResponseEntity<?> cambiarEstado(@PathVariable("id") Integer id, @PathVariable("boolean") boolean estado) {
        return ResponseEntity.ok(service.changeStatusUser(id, estado));
    }


    @PutMapping("/{id}/save-changes")
    public ResponseEntity<?> saveChanges(@RequestBody List<Rol> roles, @PathVariable("id") Integer id) {
        Optional<Usuario> o = service.findById(id);
        if (o.isEmpty()) {
            throw new NotFoundException("El " + getClassName() + " no existe");
        }
        Usuario userDb = o.get();
        userDb.clearArray();
        roles.forEach(userDb::addRol);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.update(userDb));
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getRoles() {
        return ResponseEntity.ok(rolService.findAll());
    }
}

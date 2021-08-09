package com.educacionperu21.apirest.controller;

import com.educacionperu21.apirest.entities.Pago;
import com.educacionperu21.apirest.exceptions.BadRequestException;
import com.educacionperu21.apirest.generics.controller.GenericControllerWithStatus;
import com.educacionperu21.apirest.services.IPagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = ControllerPaths.PATH_ANGULAR_ORIGIN)
@RestController
@RequestMapping(path = ControllerPaths.PATH_PAGO)
public class PagoController extends GenericControllerWithStatus<Pago, Integer, IPagoService> {

    public PagoController() {
        this.type = Pago.class;
    }

    @GetMapping("/max-id")
    public ResponseEntity<?> findMaxID() {
        Map<String, Integer> response = new HashMap<>();
        response.put("max", service.findLastId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

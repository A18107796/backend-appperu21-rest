package com.educacionperu21.apirest.controller;

import com.educacionperu21.apirest.entities.Matricula_Pagos;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import com.educacionperu21.apirest.generics.controller.GenericControllerWithStatus;
import com.educacionperu21.apirest.services.IMatriculaPagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = ControllerPaths.PATH_ANGULAR_ORIGIN)
@RestController
@RequestMapping(path = ControllerPaths.PATH_MATRICULA_PAGO)
public class MatriculaPagoController extends GenericControllerWithStatus<Matricula_Pagos, Integer, IMatriculaPagoService> {

    public MatriculaPagoController() {
        this.type = Matricula_Pagos.class;
    }

    @GetMapping("/recent/{id}")
    public ResponseEntity<?> getRecentDeudas(@PathVariable("id") Integer id, @RequestParam(required = false, name = "estado", defaultValue = ("PENDIENTE")) Estado estado) {
        Map<String, Object> response = new HashMap<>();
        Optional<Matricula_Pagos> mp = service.getMatriculaPagoCercana(id, estado);

        if (mp.get().getId() == null) {
            response.put("mensaje", "sin deudas");
        }else{
            response.put("pago", mp.get());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/verify-pagos")
    public ResponseEntity<?> checkDeudas() {
        return ResponseEntity.status(HttpStatus.OK).body(service.verifyCuotas());
    }

    @GetMapping("/verify-deudas/{id}")
    public ResponseEntity<?> hasDeudas(@PathVariable("id") Integer id) {
        Map<String, Integer> response = new HashMap<>();
        int numCuotas = service.hasDeudas(id);
        response.put("deudas", numCuotas);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}


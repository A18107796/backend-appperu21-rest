package com.educacionperu21.apirest.controller;

import com.educacionperu21.apirest.entities.Pago;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.exceptions.BadRequestException;
import com.educacionperu21.apirest.exceptions.NotFoundException;
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

    @PutMapping("/anular-pago/{idPago}")
    public ResponseEntity<?> anularPago(@PathVariable("idPago") Integer id) {
        Map<String, Object> response;
        int pago = service.anularPago(id);

        response = new HashMap<>();
        response.put("mensaje", "Factura: " + id + "anulada correctamente.");
        response.put("pago", pago);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/query/total")
    public ResponseEntity<?> getGanancias() {
        Map<String, Object> response;
        response = new HashMap<>();
        response.put("ganancias", service.getGanancias());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/query/filter/{estado}")
    public ResponseEntity<?> getGananciasEntreFechas(@PathVariable("estado") Estado estado, @RequestParam("fecha_inicio") String inicio, @RequestParam("fecha_fin") String fin) {
        Map<String, Object> response;
        System.out.println(inicio);
        System.out.println(fin);
        response = new HashMap<>();
        double ganancias = 0.0;
        ganancias = service.getGananciasBetweenFechas(estado.toString(), inicio, fin);
        response.put("ganancias", ganancias);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

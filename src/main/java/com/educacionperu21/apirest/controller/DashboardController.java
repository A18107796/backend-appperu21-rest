package com.educacionperu21.apirest.controller;


import com.educacionperu21.apirest.entities.Especializacion;
import com.educacionperu21.apirest.services.IDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = ControllerPaths.PATH_ANGULAR_ORIGIN)
@RestController
@RequestMapping(path = ControllerPaths.PATH_QUERYS)
public class DashboardController {

    @Autowired
    IDashboardService service;


    @GetMapping("/especializaciones/most-matriculas")
    public ResponseEntity<?> getEspecializacionMostMatriculada() {
        List<Map<Especializacion, Integer>> response = service.findMonstEspecialization();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/especializaciones/most-matriculas/{idPeriodo}")
    public ResponseEntity<?> getEspecializacionMostMatriculadaByID(@PathVariable("idPeriodo") Integer idPeriodo) {
        List<Map<Especializacion, Integer>> response = service.findMonstEspecialization(idPeriodo);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pagos/status")
    public ResponseEntity<?> getStaticsForPagoStatus() {
        List<Map<String, Integer>> response = service.findStadisticPagoStatus();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pensiones/status")
    public ResponseEntity<?> getStaticsForPensionesStatus() {
        List<Map<String, Integer>> response = service.findStadisticsOfStatus();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/count/")
    public ResponseEntity<?> getCount(@RequestParam(name = "value", defaultValue = "estudiante") String value) {

        Map<String, Integer> response = new HashMap<>();
        if (value.equalsIgnoreCase("estudiantes")) {
            response = service.findStudentCount();
        } else if (value.equalsIgnoreCase("empleados")) {
            response = service.findEmpleadosCount();

        } else if (value.equalsIgnoreCase("matriculas")) {
            response = service.findMatriculasCount();
        }
        return ResponseEntity.ok(response);
    }
}

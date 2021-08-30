package com.educacionperu21.apirest.controller;

import com.educacionperu21.apirest.dao.PeriodoDAO;
import com.educacionperu21.apirest.entities.Periodo;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.exceptions.BadRequestException;
import com.educacionperu21.apirest.generics.controller.GenericController;
import com.educacionperu21.apirest.generics.controller.GenericControllerWithStatus;
import com.educacionperu21.apirest.services.IPeriodoService;
import com.educacionperu21.apirest.services.impl.PeriodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@CrossOrigin(origins = ControllerPaths.PATH_ANGULAR_ORIGIN)
@RestController
@RequestMapping(path = ControllerPaths.PATH_PERIODO)
public class PeriodoController extends GenericControllerWithStatus<Periodo, Integer, IPeriodoService> {

    @Autowired
    @Qualifier("simpleDateFormat")
    private SimpleDateFormat simpleDateFormat;

    public PeriodoController() {
        this.type = Periodo.class;
    }

    @PostMapping("/crear-anio-academico")
    public ResponseEntity<?> crearAnioAcademico(
            @RequestParam(name = "fecha") String fecha_inicio,
            @RequestParam(name = "cada", defaultValue = "30") int cada,
            @RequestParam(name = "duracion", defaultValue = "12") int meses_duración) {

        Date inicio = null;
        try {
            inicio = simpleDateFormat.parse(fecha_inicio);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearAnioAcademico(inicio, cada, meses_duración, "Periodo"));
    }

    @GetMapping("/get-next-periodo")
    public ResponseEntity<?> getNextPeriodo(
            @RequestParam(name = "estado", defaultValue = "ACTIVO") String estado) {
        System.out.println(estado);

        return ResponseEntity.status(HttpStatus.OK).body(service.filtrar_periodo_reciente(estado));
    }

    @PutMapping("/{id}/update-status/{estado}")
    public ResponseEntity<?> crearAnioAcademico(@PathVariable("id") Integer id, @PathVariable("estado") Estado estado) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.updateStatus(estado, id));
    }

    @GetMapping("/check-periodo-cercano")
    public ResponseEntity<?> checkPeriodoCercano(){
        Periodo p = service.checkCercanoPeriodoToIncripcion();
        if(p == null){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(service.checkCercanoPeriodoToIncripcion());
    }

    @PutMapping("/sincronizar")
    public ResponseEntity<?> sincronizar(){
        return ResponseEntity.status(HttpStatus.OK).body(service.sincronizarPeriodos());
    }
    @GetMapping("/{estado}/check-status")
    public ResponseEntity<?> check(
            @PathVariable("estado") String estado) {

        if (estado == null | estado.length() <= 0) {
            throw new BadRequestException("Envie ESTADO valido");
        }
        if (estado.contains("activo") | estado.contains("actibo") | estado.contains("Activo")) {
            estado = Estado.ACTIVO.toString();
        }

        if (estado.contains("endiente") | estado.contains("pendiente")) {
            estado = Estado.PENDIENTE.toString();
        }

        if (estado.contains("inscripcion") | estado.contains("abierta") | estado.contains("incripcion") | estado.contains("inscripcion_Abierta")) {
            estado = Estado.INSCRIPCION_ABIERTA.toString();
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.checkPeriodo(estado));
    }
}

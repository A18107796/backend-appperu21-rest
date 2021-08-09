package com.educacionperu21.apirest.controller;

import com.educacionperu21.apirest.entities.Tipo_Pago;
import com.educacionperu21.apirest.generics.controller.GenericController;
import com.educacionperu21.apirest.services.ITipoPagoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = ControllerPaths.PATH_ANGULAR_ORIGIN)
@RestController
@RequestMapping(path = ControllerPaths.PATH_TIPO_PAGO)
public class TipoPagoController extends GenericController<Tipo_Pago, Integer, ITipoPagoService> {
    public TipoPagoController() {
        this.type = Tipo_Pago.class;
    }
}

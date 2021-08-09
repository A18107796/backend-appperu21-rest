package com.educacionperu21.apirest.controller;

import com.educacionperu21.apirest.dao.TipoComprobanteDAO;
import com.educacionperu21.apirest.entities.Tipo_Comprobante;
import com.educacionperu21.apirest.entities.Tipo_Pago;
import com.educacionperu21.apirest.generics.controller.GenericController;
import com.educacionperu21.apirest.services.ITIpoComprobanteService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = ControllerPaths.PATH_ANGULAR_ORIGIN)
@RestController
@RequestMapping(path = ControllerPaths.PATH_TIPO_COMPROBANTE)
public class TipoComprobanteController extends GenericController<Tipo_Comprobante, Integer, ITIpoComprobanteService> {

    public TipoComprobanteController() {
        this.type = Tipo_Comprobante.class;
    }
}

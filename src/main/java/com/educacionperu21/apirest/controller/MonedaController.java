package com.educacionperu21.apirest.controller;


import com.educacionperu21.apirest.entities.Moneda;
import com.educacionperu21.apirest.generics.controller.GenericController;
import com.educacionperu21.apirest.services.IMonedaService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = ControllerPaths.PATH_ANGULAR_ORIGIN)
@RestController
@RequestMapping(path = ControllerPaths.PATH_MONEDA)
public class MonedaController extends GenericController<Moneda, Integer, IMonedaService> {

    public MonedaController() {
        this.type = Moneda.class;
    }
}

package com.educacionperu21.apirest.controller;

import com.educacionperu21.apirest.dao.PeriodoDAO;
import com.educacionperu21.apirest.entities.Periodo;
import com.educacionperu21.apirest.generics.controller.GenericController;
import com.educacionperu21.apirest.services.IPeriodoService;
import com.educacionperu21.apirest.services.impl.PeriodoServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = ControllerPaths.PATH_PERIODO)
public class PeriodoController extends GenericController<Periodo, Integer, IPeriodoService> {

    public PeriodoController(){
        this.type = Periodo.class;
    }

}

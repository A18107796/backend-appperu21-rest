package com.educacionperu21.apirest.controller;

import com.educacionperu21.apirest.entities.Sede;
import com.educacionperu21.apirest.generics.controller.GenericControllerWithStatus;
import com.educacionperu21.apirest.services.ISedeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ControllerPaths.PATH_SEDES)
public class SedeRestController extends GenericControllerWithStatus<Sede, Integer, ISedeService> {


}

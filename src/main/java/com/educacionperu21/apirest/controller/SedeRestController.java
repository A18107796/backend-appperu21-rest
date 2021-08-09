package com.educacionperu21.apirest.controller;

import com.educacionperu21.apirest.entities.Sede;
import com.educacionperu21.apirest.generics.controller.GenericController;
import com.educacionperu21.apirest.generics.controller.GenericControllerWithStatus;
import com.educacionperu21.apirest.services.ISedeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = ControllerPaths.PATH_ANGULAR_ORIGIN)
@RestController
@RequestMapping(path = ControllerPaths.PATH_SEDES)
public class SedeRestController extends GenericController<Sede, Integer, ISedeService> {


}

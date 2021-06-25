package com.educacionperu21.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educacionperu21.apirest.services.IUbicacionService;

@RestController
@RequestMapping(path = ControllerPaths.PATH_UBICACIONES)
public class UbicacionRestController {
	
	@Autowired
	private IUbicacionService ubicacion; 
	
}

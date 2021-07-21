package com.educacionperu21.apirest.controller;

import com.educacionperu21.apirest.entities.Departamento;
import com.educacionperu21.apirest.entities.Distrito;
import com.educacionperu21.apirest.entities.Provincia;
import com.educacionperu21.apirest.entities.Tipo_Documento;
import com.educacionperu21.apirest.services.ITipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educacionperu21.apirest.services.IUbicacionService;

import java.util.List;

@RestController
@RequestMapping(path = ControllerPaths.PATH_UBICACIONES)
public class UbicacionRestController {

    @Autowired
    private IUbicacionService ubicacion;

    @Autowired
    private ITipoDocumentoService tipoDocumentoService;

    @GetMapping("/departamentos")
    public List<Departamento> finddepartamentos() {
        return this.ubicacion.getDepartamentos();
    }

    @GetMapping("/provincias/{id}")
    public List<Provincia> findProvincias(@PathVariable("id") Integer id) {
        return this.ubicacion.getProvinciasByIDDep(id);
    }

    @GetMapping("/distritos/{id}")
    public List<Distrito> findDistritos(@PathVariable("id") Integer id) {
        return this.ubicacion.getDistritoByIDProv(id);
    }

    @GetMapping("/tipos-documento")
    public List<Tipo_Documento> findTipo_Documento() {
        return (List<Tipo_Documento>) this.tipoDocumentoService.findAll();
    }

}

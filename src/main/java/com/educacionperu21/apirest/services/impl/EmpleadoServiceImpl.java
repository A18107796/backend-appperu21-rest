package com.educacionperu21.apirest.services.impl;

import java.util.List;

import com.educacionperu21.apirest.entities.Estudiante;
import com.educacionperu21.apirest.entities.Usuario;
import com.educacionperu21.apirest.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.educacionperu21.apirest.dao.EmpleadoDAO;
import com.educacionperu21.apirest.entities.Empleado;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.IEmpleadoService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoServiceImpl extends GenericServiceImpl<Empleado, EmpleadoDAO, Integer>
        implements IEmpleadoService {

    @Autowired
    private IUsuarioService usuarioService;


    @Autowired
    @Qualifier("passwordEncoder")
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Empleado save(Empleado alumno) {
        System.out.println("HOLA");
        Empleado empleadoBD = this.dao.save(alumno);
        empleadoBD = this.findById(empleadoBD.getId()).get();
        Usuario usuario = new Usuario();
        if(empleadoBD.getCargo().getNombre().equalsIgnoreCase("SECRETARIA")){
            usuario.setRoles(UsuarioService.CARGO_SECRETARIA);
        }else if(empleadoBD.getCargo().getNombre().equalsIgnoreCase(("ENCARGADO DE INFORMES"))){
            usuario.setRoles(UsuarioService.CARGO_INFORMES);
        }else if(empleadoBD.getCargo().getNombre().equalsIgnoreCase("CAJERO")){
            usuario.setRoles(UsuarioService.CARGO_CAJA);
        }else if(empleadoBD.getCargo().getNombre().equalsIgnoreCase("COORDINADOR ACADEMICO")){
            usuario.setRoles(UsuarioService.CARGO_CORDINACIONACADEMICA);
        }else{
            usuario.setRoles(UsuarioService.CARGO_EMPLADO);
        }
        usuario.setEnabled(true);
        usuario.setEmail(empleadoBD.getEmail());
        String passwordEncrypted = passwordEncoder.encode(empleadoBD.getNum_doc());
        usuario.setPassword(passwordEncrypted);
        usuario.setEmpleado(empleadoBD);
        return usuarioService.save(usuario).getEmpleado();
    }

    @Override
    public boolean emailExists(String email) {
        List<String> emails = dao.emailsExists(email);
        if (emails.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean dniExists(String email) {
        List<String> dnis = dao.dniExists(email);
        if (dnis.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}

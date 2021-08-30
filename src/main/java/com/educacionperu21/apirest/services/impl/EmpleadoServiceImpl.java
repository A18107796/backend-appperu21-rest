package com.educacionperu21.apirest.services.impl;

import java.util.List;
import java.util.Optional;

import com.educacionperu21.apirest.entities.Estudiante;
import com.educacionperu21.apirest.entities.IGenericStatusClass;
import com.educacionperu21.apirest.entities.Usuario;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatusImpl;
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
public class EmpleadoServiceImpl extends GenericServiceWithStatusImpl<Empleado, EmpleadoDAO, Integer>
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
        System.out.println(alumno.toString());
        Empleado empleadoBD = this.dao.save(alumno);
        Optional<Empleado> e = dao.findById(empleadoBD.getId());
        empleadoBD = e.get();

        Usuario usuario = new Usuario();
        if (empleadoBD.getCargo().getId() == 1) {
            usuario.setRoles(UsuarioService.CARGO_SECRETARIA);
        } else if (empleadoBD.getCargo().getId() == 2) {
            usuario.setRoles(UsuarioService.CARGO_INFORMES);
        } else if (empleadoBD.getCargo().getId() == 3) {
            usuario.setRoles(UsuarioService.CARGO_CAJA);
        } else if (empleadoBD.getCargo().getId() == 4) {
            usuario.setRoles(UsuarioService.CARGO_CORDINACIONACADEMICA);
        } else {
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

    @Override
    public Empleado update(Empleado empleado) {
        return dao.save(empleado);
    }

    @Override
    @Transactional
    public boolean ChangeStatus(Integer id, Estado estado) {
        if (estado == Estado.INACTIVO) {
            Optional<Empleado> emp = dao.findById(id);
            if (emp.isEmpty()) {
                throw new NotFoundException("El Empleado no existe.");
            }
            System.out.println("ELIMINANDO");
            this.usuarioService.changeStatusUser(emp.get().getUsuario().getId(), false);
        } else if (estado == Estado.ACTIVO) {
            Optional<Empleado> emp = dao.findById(id);
            if (emp.isEmpty()) {
                throw new NotFoundException("El Empleado no existe.");
            }
            this.usuarioService.changeStatusUser(emp.get().getUsuario().getId(), true);
        }
        return super.ChangeStatus(id, estado);
    }
}

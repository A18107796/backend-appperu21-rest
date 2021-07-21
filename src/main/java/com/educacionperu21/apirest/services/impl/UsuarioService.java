package com.educacionperu21.apirest.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.educacionperu21.apirest.entities.Rol;
import com.educacionperu21.apirest.exceptions.BadRequestException;
import com.educacionperu21.apirest.services.IEmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.educacionperu21.apirest.dao.IUsuarioDao;
import com.educacionperu21.apirest.entities.Usuario;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.IUsuarioService;

@Service
public class UsuarioService extends GenericServiceImpl<Usuario, IUsuarioDao, Integer>
        implements IUsuarioService, UserDetailsService {



    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    public final static List<Rol> CARGO_EMPLADO = Arrays.asList(new Rol(4));
    public final static List<Rol> CARGO_CAJA = Arrays.asList(new Rol(4), new Rol(6), new Rol(2));
    public final static List<Rol> CARGO_INFORMES = Arrays.asList(new Rol(4), new Rol(2));
    public final static List<Rol> CARGO_SECRETARIA = Arrays.asList(new Rol(4), new Rol(5), new Rol(2));
    public final static List<Rol> CARGO_CORDINACIONACADEMICA = Arrays.asList(new Rol(4), new Rol(3), new Rol(5), new Rol(5));


    @Override
    @Transactional(readOnly = true)
    public Usuario findByEmail(String email) {
        Usuario usuario = dao.findByEmail(email);
        dao.findByEmail(email);
        return usuario;
    }

    @Override
    @Transactional
    public Usuario createUser(Usuario usuario) {
        return null;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = dao.findByEmail(username);

        if (usuario == null) {
            logger.error("Error en el login: no existe el usuario " + username + " en el sistema");
            throw new UsernameNotFoundException(
                    "Error en el login: no existe el usuario " + username + " en el sistema");
        }

        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .peek(authority -> logger.info("Rol: " + authority.getAuthority())).collect(Collectors.toList());

        return new User(username, usuario.getPassword(), usuario.isEnabled(), true, true, true, authorities);

    }

}

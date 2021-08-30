package com.educacionperu21.apirest.services;

import com.educacionperu21.apirest.entities.Usuario;
import com.educacionperu21.apirest.generics.service.GenericService;

public interface IUsuarioService extends GenericService<Usuario, Integer> {

    Usuario findByEmail(String email);

    Usuario createUser(Usuario usuario);

    int changeStatusUser(Integer id, boolean enabled);

    Usuario update(Usuario usuario);


}

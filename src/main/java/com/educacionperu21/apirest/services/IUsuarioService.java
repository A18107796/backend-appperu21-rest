package com.educacionperu21.apirest.services;

import com.educacionperu21.apirest.entities.Usuario;
import com.educacionperu21.apirest.generics.service.GenericService;

public interface IUsuarioService extends GenericService<Usuario, Integer>{
	
	public Usuario findByEmail(String email);

}

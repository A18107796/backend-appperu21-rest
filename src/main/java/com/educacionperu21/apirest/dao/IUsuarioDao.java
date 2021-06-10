package com.educacionperu21.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.educacionperu21.apirest.entities.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Integer> {

	@Query("select u from Usuario u where u.email=?1")
	public Usuario findByEmail(String email);
}

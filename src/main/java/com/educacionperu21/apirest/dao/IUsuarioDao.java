package com.educacionperu21.apirest.dao;

import com.educacionperu21.apirest.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.educacionperu21.apirest.entities.Usuario;
import org.springframework.transaction.annotation.Transactional;

public interface IUsuarioDao extends JpaRepository<Usuario, Integer> {

	@Query("select u from Usuario u where u.email=?1")
	public Usuario findByEmail(String email);

	@Transactional
	@Modifying
	@Query("update Usuario m set m.enabled = ?1 where m.id = ?2")
	int updateEstado(boolean estado, Integer id);
}

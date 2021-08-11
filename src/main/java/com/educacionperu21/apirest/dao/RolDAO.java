package com.educacionperu21.apirest.dao;

import com.educacionperu21.apirest.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDAO extends JpaRepository<Rol, Long> {
}

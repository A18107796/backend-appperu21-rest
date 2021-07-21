package com.educacionperu21.apirest.dao;

import com.educacionperu21.apirest.entities.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoDAO extends JpaRepository<Cargo, Integer> {
}

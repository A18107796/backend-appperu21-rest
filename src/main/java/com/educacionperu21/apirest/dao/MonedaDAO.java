package com.educacionperu21.apirest.dao;

import com.educacionperu21.apirest.entities.Moneda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonedaDAO extends JpaRepository<Moneda, Integer> {
}

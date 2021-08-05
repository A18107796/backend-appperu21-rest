package com.educacionperu21.apirest.dao;

import com.educacionperu21.apirest.entities.Tipo_Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoPagoDAO extends JpaRepository<Tipo_Pago, Integer> {
}

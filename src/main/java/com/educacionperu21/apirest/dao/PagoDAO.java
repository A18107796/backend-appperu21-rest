package com.educacionperu21.apirest.dao;

import com.educacionperu21.apirest.entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoDAO extends GenericJPAStatusRepository<Pago, Integer> {
}

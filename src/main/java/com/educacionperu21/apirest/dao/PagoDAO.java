package com.educacionperu21.apirest.dao;

import com.educacionperu21.apirest.entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PagoDAO extends GenericJPAStatusRepository<Pago, Integer> {

    @Query("SELECT MAX(p.npago) from Pago p")
    Optional<Integer> findLastId();
}

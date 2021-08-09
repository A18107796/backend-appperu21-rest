package com.educacionperu21.apirest.dao;

import com.educacionperu21.apirest.controller.TipoPagoController;
import com.educacionperu21.apirest.entities.Tipo_Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoComprobanteDAO extends JpaRepository<Tipo_Comprobante, Integer> {

}

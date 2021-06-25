package com.educacionperu21.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educacionperu21.apirest.entities.Tipo_Documento;

public interface TipoDocumentoDAO extends JpaRepository<Tipo_Documento, Integer> {

}

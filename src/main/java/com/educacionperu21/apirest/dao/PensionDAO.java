package com.educacionperu21.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educacionperu21.apirest.entities.Pension;

public interface PensionDAO extends JpaRepository<Pension, Integer>{

}

package com.educacionperu21.apirest.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.educacionperu21.apirest.entities.Empleado;

public interface EmpleadoDAO extends PagingAndSortingRepository<Empleado, Integer> {

}

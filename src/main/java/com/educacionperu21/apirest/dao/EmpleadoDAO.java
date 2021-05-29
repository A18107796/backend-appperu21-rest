package com.educacionperu21.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.educacionperu21.apirest.entities.Empleado;

public interface EmpleadoDAO extends PagingAndSortingRepository<Empleado, Integer> {

	@Query(nativeQuery = true, value = "select e.email from empleados e where e.email = ?1")
	List<String> emailsExists(String email);
}

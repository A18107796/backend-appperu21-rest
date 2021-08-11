package com.educacionperu21.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.educacionperu21.apirest.entities.Empleado;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoDAO extends GenericJPAStatusRepository<Empleado, Integer> {

	@Query(nativeQuery = true, value = "select e.email from empleados e where e.email = ?1")
	List<String> emailsExists(String email);

	@Query(nativeQuery = true, value = "select e.num_doc from empleados e where e.num_doc = ?1")
	List<String> dniExists(String dni);

	@Query("select count(e) from Empleado e")
	int getCountStudents();
}

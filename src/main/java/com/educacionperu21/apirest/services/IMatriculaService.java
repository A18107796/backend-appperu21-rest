package com.educacionperu21.apirest.services;

import java.util.List;
import java.util.Optional;

import com.educacionperu21.apirest.entities.IGenericStatusClass;
import com.educacionperu21.apirest.entities.Matricula;
import com.educacionperu21.apirest.generics.service.GenericService;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatus;

public interface IMatriculaService extends GenericServiceWithStatus<Matricula, Integer> {

	Optional<Matricula> findMostRecentMatriculaById(Integer id);

	Optional<Matricula> findStudentMatriculado(Integer idStudent);

	List<Matricula> findMatriculasByDNI(String dni);
}

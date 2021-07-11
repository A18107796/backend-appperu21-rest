package com.educacionperu21.apirest.services;

import java.util.Optional;

import com.educacionperu21.apirest.entities.Matricula;
import com.educacionperu21.apirest.generics.service.GenericService;

public interface IMatriculaService extends GenericService<Matricula, Integer> {

	Optional<Matricula> findMostRecentMatriculaById(Integer id);
}

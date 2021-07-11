package com.educacionperu21.apirest.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.educacionperu21.apirest.dao.PeriodoDAO;
import com.educacionperu21.apirest.entities.Periodo;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.IPeriodoService;

@Service
public class PeriodoServiceImpl extends GenericServiceImpl<Periodo, PeriodoDAO, Integer> implements IPeriodoService{

	@Override
	public boolean ExistsPeriodo(Integer id) {
		return this.dao.existsById(id);
	}

	@Override
	public Optional<Periodo> getMostRecentPeriodo() {
		return this.dao.getMostRecentPeriodo();
	}

}

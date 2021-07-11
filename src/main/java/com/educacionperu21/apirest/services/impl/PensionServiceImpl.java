package com.educacionperu21.apirest.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.educacionperu21.apirest.dao.PensionDAO;
import com.educacionperu21.apirest.entities.Pago;
import com.educacionperu21.apirest.entities.Pension;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.IPensionService;

@Service
public class PensionServiceImpl extends GenericServiceImpl<Pension, PensionDAO, Integer> implements IPensionService {

	@Override
	@Transactional
	public List<Pension> registerPensiones(int numCuotas, double montoTotal) {
		
		List<Pension> pensiones = new ArrayList<Pension>();
		double costoXPension = 0.0;
		costoXPension = montoTotal / numCuotas;

		pensiones.add(new Pension(UUID.randomUUID().toString(), "Pension: PERU 21 - Matricula", 100.00));

		for (int i = 0; i < numCuotas; i++) {
			pensiones.add(
					new Pension(UUID.randomUUID().toString(), "Pension: CUOTA NUMERO ".concat(i + " "), costoXPension));
		}
		return dao.saveAll(pensiones);
	}

}

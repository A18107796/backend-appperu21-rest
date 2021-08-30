package com.educacionperu21.apirest.services;

import com.educacionperu21.apirest.dao.*;
import com.educacionperu21.apirest.entities.Especializacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IDashboardServiceImpl implements IDashboardService {

    private Map<String, Integer> response;

    @Autowired
    private MatriculaPagoDAO mpDAO;

    @Autowired
    private EspecializacionDAO espDAO;

    @Autowired
    private PagoDAO pagoDAO;

    @Autowired
    private EstudianteDAO estDAO;

    @Autowired
    private EmpleadoDAO empDAO;

    @Autowired
    private MatriculasDAO matriculaDAO;

    @Override
    public List<Map<String, Integer>> findStadisticPagoStatus() {
        return pagoDAO.findStadisticPagoStatus();
    }

    @Override
    public List<Map<Especializacion, Integer>> findMonstEspecialization(Integer idPeriodo) {
        return espDAO.findMonstEspecializationByPeriodo(idPeriodo);
    }

    @Override
    public List<Map<Especializacion, Integer>> findMonstEspecialization() {
        return espDAO.findMonstEspecialization();
    }

    @Override
    public List<Map<String, Integer>> findStadisticsOfStatus() {
        return mpDAO.findStadisticsOfStatus();
    }

    @Override
    public Map<String, Integer> findStudentCount() {
        response = new HashMap<>();
        response.put("total", estDAO.getCountStudents());
        return response;
    }

    @Override
    public Map<String, Integer> findEmpleadosCount() {
        response = new HashMap<>();
        response.put("total", empDAO.getCountStudents());
        return response;
    }

    @Override
    public Map<String, Integer> findMatriculasCount() {
        response = new HashMap<>();
        response.put("total", matriculaDAO.getCountMatriculas());
        return response;
    }
}

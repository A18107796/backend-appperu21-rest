package com.educacionperu21.apirest.services.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.exceptions.BadRequestException;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatus;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatusImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.educacionperu21.apirest.dao.PeriodoDAO;
import com.educacionperu21.apirest.entities.Periodo;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.IPeriodoService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeriodoServiceImpl extends GenericServiceWithStatusImpl<Periodo, PeriodoDAO, Integer> implements IPeriodoService {

    private Calendar calendar;


    @Override
    public Periodo save(Periodo alumno) {
        calendar = Calendar.getInstance();
        int cantidadDias = 30;
        Date hoy = new Date();

        if (hoy.getTime() > alumno.getFecha_inicio().getTime()) {
            throw new BadRequestException("Seleccione una fecha valida.");
        }

        calendar.setTime(alumno.getFecha_inicio());
        int month = calendar.get(Calendar.MONTH);

        calendar.add(Calendar.MONTH, 12);
        alumno.setFecha_fin(calendar.getTime());
        return super.save(alumno);
    }


    @Override
    public boolean ExistsPeriodo(Integer id) {
        return this.dao.existsById(id);
    }

    @Override
    public Optional<Periodo> getMostRecentPeriodo() {
        return this.dao.getMostRecentPeriodo();
    }

    @Override
    @Transactional
    public List<Periodo> crearAnioAcademico(Date fecha_inicio, int cada, int meses_duración, String nombre_periodo) {
        List<Periodo> anioAcademico = new ArrayList<>();
        Date hoy = new Date();
        calendar = Calendar.getInstance();
        if (hoy.getTime() > fecha_inicio.getTime()) {
            throw new BadRequestException("Seleccione fecha valida");
        }
        Date inicio = fecha_inicio;
        for (int i = 0; meses_duración > i; i++) {
            Periodo p = new Periodo();
            if (i == 0) {
                p.setFecha_inicio(fecha_inicio);
                p.setEstado(Estado.INSCRIPCION_ABIERTA);
            } else {
                p.setFecha_inicio(inicio);
                p.setEstado(Estado.PENDIENTE);
            }
            calendar.setTime(p.getFecha_inicio());
            calendar.add(Calendar.MONTH, meses_duración);
            p.setFecha_fin(calendar.getTime());
            calendar.setTime(p.getFecha_inicio());
            calendar.add(Calendar.DAY_OF_YEAR, cada);
            inicio = calendar.getTime();
            int anio = calendar.get(Calendar.YEAR);
            p.setAnio(anio);
            p.setNombre(nombre_periodo + " " + anio + " N° " + (i + 1));
            anioAcademico.add(p);
        }
        return dao.saveAll(anioAcademico);
    }

    @Override
    public List<Periodo> filtrar_periodo_reciente(String estado) {
        if (estado == null) {
            estado = Estado.ACTIVO.toString();
        }
        return dao.filtrar_periodo_reciente(estado);
    }

    @Override
    public List<Periodo> checkPeriodo(String estado) {
        return dao.check_periodo(estado);
    }

    @Override
    public int updateStatus(Estado estado, Integer id) {
        if (!dao.existsById(id)) {
            throw new NotFoundException("El PERIODO NO EXISTE, VERIFIQUE DATOS");
        }

        return dao.updateEstado(estado, id);
    }

    @Override
    @Transactional
    public boolean updateAllStatus(List<Periodo> periodos, Estado estado) {
        if (periodos.isEmpty()) {
            throw new BadRequestException("ENVIE UNA LISTA DE PERIODOS VALIDA");
        }
        try {
            for (Periodo p : periodos) {
                updateStatus(estado, p.getId());
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean sincronizarPeriodos() {
        List<Periodo> lp = dao.findAll(Sort.by(Sort.Direction.ASC, "id"));
        Date hoy = new Date();

        for (int i = 0; i < lp.size(); i++) {

            if (hoy.getTime() > lp.get(i).getFecha_inicio().getTime() && lp.get(i).getEstado() == Estado.PENDIENTE) {
                updateStatus(Estado.ACTIVO, lp.get(i).getId());
            }

            if (hoy.getTime() > lp.get(i).getFecha_inicio().getTime() && lp.get(i).getEstado() == Estado.INSCRIPCION_ABIERTA) {
                System.out.println("TRUE");
                updateStatus(Estado.ACTIVO, lp.get(i).getId());
            }

            if (lp.get(i).getFecha_inicio().getTime() > hoy.getTime() && lp.get(i).getEstado() == Estado.ACTIVO) {
                updateStatus(Estado.PENDIENTE, lp.get(i).getId());
            }
            if (lp.get(i).getFecha_fin().getTime() < hoy.getTime() && (lp.get(i).getEstado() != Estado.CULMINADO || lp.get(i).getEstado() == Estado.PENDIENTE)) {
                updateStatus(Estado.CULMINADO, lp.get(i).getId());
            }
        }
        return true;
    }

    @Override
    public Periodo checkCercanoPeriodoToIncripcion() {
        List<Periodo> lp = dao.findAll(Sort.by(Sort.Direction.ASC, "id"));

        lp.forEach(p -> {
            System.out.println(p.getId());
        });


        lp = dao.filtrar_periodo_reciente(Estado.INSCRIPCION_ABIERTA.toString());
        if (!lp.isEmpty()) {
            throw new NotFoundException("Existe un periodo en proceso de INSCRIPCION");
        }

        lp = dao.filtrar_periodo_reciente("PENDIENTE");
        if (lp.isEmpty()) {
            throw new NotFoundException("No existen periodos");
        }

        Periodo p = lp.get(0);
        return p;
    }


}

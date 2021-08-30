package com.educacionperu21.apirest.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.educacionperu21.apirest.entities.Periodo;
import com.educacionperu21.apirest.exceptions.InternalServerError;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatus;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatusImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.educacionperu21.apirest.dao.MatriculasDAO;
import com.educacionperu21.apirest.entities.Matricula;
import com.educacionperu21.apirest.entities.Matricula_Pagos;
import com.educacionperu21.apirest.entities.Pension;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.exceptions.BadRequestException;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.IEspecializacionService;
import com.educacionperu21.apirest.services.IEstudianteService;
import com.educacionperu21.apirest.services.IMatriculaService;
import com.educacionperu21.apirest.services.IPensionService;
import com.educacionperu21.apirest.services.IPeriodoService;

@Service
public class MatriculaServiceImpl extends GenericServiceWithStatusImpl<Matricula, MatriculasDAO, Integer>
        implements IMatriculaService {

    @Autowired
    private IPeriodoService periodoService;

    @Autowired
    private IEspecializacionService especializacionService;

    @Autowired
    private IPensionService pensionService;

    @Autowired
    private IEstudianteService estudianteService;

    @Override
    @Transactional
    public Matricula save(Matricula alumno) {

        if (!especializacionService.ExistsEspecializacion(alumno.getEspecializacion().getId())) {
            throw new NotFoundException("La especializacion no existe, verifique datos.");
        }

        if (!estudianteService.ExistsEntity(alumno.getEstudiante().getId())) {
            throw new NotFoundException("El estudiante no existe, verifique datos.");
        }

        if (!periodoService.ExistsEntity(alumno.getPeriodo().getId())) {
            throw new NotFoundException("El periodo no existe, verifique datos.");
        }


        Optional<Matricula> OldMatricula = findStudentMatriculado(alumno.getEstudiante().getId());

        if (OldMatricula.isPresent()) {
            throw new BadRequestException(("El estudiante ya fue matriculado en este periodo, verifique datos."));
        }

        List<Pension> pensionesRegistradas = pensionService.registerPensiones(alumno.getNum_cuotas(), 960);
        if (pensionesRegistradas == null || pensionesRegistradas.isEmpty()) {
            throw new BadRequestException("Ocurrio un error, intentelo denuevo");
        }
        alumno.setFecha_reg(new Date());
        Optional<Periodo> periodoBD = periodoService.findById(alumno.getPeriodo().getId());
        if (periodoBD.get().getEstado() != Estado.INSCRIPCION_ABIERTA) {
            throw new BadRequestException("El Periodo no es valido o no existen INSCRIPCIONES ABIERTAS");
        }

        alumno.setPeriodo(periodoBD.get());
        int res = estudianteService.updateEstado(Estado.MATRICULADO, alumno.getEstudiante().getId());
        if (res <= 0) {
            throw new InternalServerError("Ocurrio un error, intentelo denuevo porfavor.");
        }

        Matricula newMatricula = dao.save(alumno);
        alumno.setEstado(Estado.PENDIENTE);
        int cont = 0;
        for (Pension p : pensionesRegistradas) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(alumno.getPeriodo().getFecha_inicio());
            Matricula_Pagos mp = new Matricula_Pagos();
            mp.setEstado(Estado.PENDIENTE);
            mp.setFecha_pago(null);
            mp.setPension(p);
            mp.setFecha_venc(null);
            if (cont == 0) {
                mp.setFecha_venc(alumno.getPeriodo().getFecha_inicio());
                cont += 1;
            } else {
                calendar.add(Calendar.DAY_OF_YEAR, cont * 30);
                cont = cont + 1;
                mp.setFecha_venc(calendar.getTime());
            }
            mp.setMatricula(newMatricula);
            newMatricula.getPagos().add(mp);
        }

        return dao.save(newMatricula);
    }

    @Override
    public Optional<Matricula> findMostRecentMatriculaById(Integer id) {
        return dao.findMostRecentMatriculaById(id);
    }

    @Override
    public Optional<Matricula> findStudentMatriculado(Integer idStudent) {
        return dao.studentMatriculado(idStudent);
    }

    @Override
    public List<Matricula> findMatriculasByDNI(String dni) {
        if (dni.length() >= 8 && dni.length() <= 11) {
            List<Matricula> matriculas = dao.getMatriculaByEstudiante(dni);
            if (matriculas.isEmpty()) {
                throw new NotFoundException("No existen matriculas con el DNI ingresado.");
            }
            return matriculas;
        } else {
            throw new BadRequestException("Ingrese un numero de documento valido.");
        }

    }

}

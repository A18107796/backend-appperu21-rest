package com.educacionperu21.apirest.services.impl;

import com.educacionperu21.apirest.dao.PagoDAO;
import com.educacionperu21.apirest.entities.Matricula;
import com.educacionperu21.apirest.entities.Matricula_Pagos;
import com.educacionperu21.apirest.entities.Pago;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.exceptions.BadRequestException;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatusImpl;
import com.educacionperu21.apirest.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PagoServiceImpl extends GenericServiceWithStatusImpl<Pago, PagoDAO, Integer> implements IPagoService {

    @Autowired
    private IEstudianteService sEstudiante;

    @Autowired
    private IMonedaService sMoneda;

    @Autowired
    private ITipoPagoService sTipoPago;

    @Autowired
    private IMatriculaPagoService sMPago;

    @Autowired
    private IMatriculaService sMatricula;

    @Override
    @Transactional
    public Pago save(Pago pago) {

        if (!sEstudiante.ExistsEntity(pago.getEstudiante().getId())) {
            throw new NotFoundException("El estudiante no existe, verifique");
        }

        if (!sMoneda.ExistsEntity(pago.getMoneda().getId())) {
            throw new NotFoundException("El estudiante no existe, verifique");
        }

        pago.getPagoDetalles().forEach(p -> {

            Optional<Matricula_Pagos> cuota = sMPago.findById(p.getPago().getId());
            if (cuota.isEmpty()) {
                throw new NotFoundException("La pension enviada no existe, verifique datos");
            }

            if (cuota.get().getEstado() == Estado.PAGADO) {
                throw new BadRequestException("La PENSION ya fue pagada, verifique informaci??n");
            }
            sMatricula.ChangeStatus(cuota.get().getMatricula().getId(), Estado.ACTIVO);
            cuota.get().setEstado(Estado.PAGADO);
            cuota.get().setFecha_pago(new Date());
            sMPago.save(cuota.get());
        });
        pago.setRuc("20548343900");
        pago.setFecha_reg(new Date());
        pago.setEstado(Estado.PAGADO);
        return dao.save(pago);
    }

    @Override
    public Integer findLastId() {
        Optional<Integer> id = dao.findLastId();
        if (id.isEmpty()) {
            return 0;
        }
        return id.get();
    }

    @Override
    @Transactional
    public int anularPago(Integer idPago) {
        if (!dao.existsById(idPago)) {
            throw new NotFoundException("Pago no existe");
        }
        Pago pagoBD = dao.findById(idPago).get();
        final Integer idMatricula = pagoBD.getPagoDetalles().get(0).getPago().getMatricula().getId();

        pagoBD.getPagoDetalles().forEach(d -> {
            sMPago.ChangeStatus(d.getPago().getId(), Estado.PENDIENTE);
            sMPago.updateFecha(d.getPago().getId());
        });
        return dao.updateEstado(Estado.ANULADO, pagoBD.getId());
    }

    @Override
    public double getGanancias() {
        return dao.getGanancias();
    }

    @Override
    public double getGananciasBetweenFechas(String estado, String fecha_inicio, String fecha_fin) {
        return dao.getGananciasBetweenFechas(estado, fecha_inicio, fecha_fin);
    }
}

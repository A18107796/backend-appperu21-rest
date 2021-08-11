package com.educacionperu21.apirest.services.impl;

import com.educacionperu21.apirest.entities.Estudiante;
import com.educacionperu21.apirest.entities.Matricula;
import com.educacionperu21.apirest.entities.Periodo;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.exceptions.BadRequestException;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatusImpl;
import com.educacionperu21.apirest.services.IMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educacionperu21.apirest.dao.MatriculaPagoDAO;
import com.educacionperu21.apirest.entities.Matricula_Pagos;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.IMatriculaPagoService;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MatriculaPagoServiceImpl extends GenericServiceWithStatusImpl<Matricula_Pagos, MatriculaPagoDAO, Integer>
        implements IMatriculaPagoService {

    @Autowired
    private IMatriculaService matriculaService;

    @Override
    public Optional<Matricula_Pagos> getMatriculaPagoCercana(Integer idMatricula, Estado estado) {
        if (estado == null) {
            throw new BadRequestException("Verifique datos");
        }
        List<Matricula_Pagos> pagos = dao.getMatriculaPagoCercana(idMatricula, estado);
        if(pagos.isEmpty()){
            return Optional.of(new Matricula_Pagos());
        }

        return Optional.of(pagos.get(0));
    }

    @Override
    public Map<String, Object> verifyCuotas() {
        Map<String, Object> response = new HashMap<>();
        List<Matricula_Pagos> pagos = dao.findAll();
        int nroPagosActualizados = 0;

        for (Matricula_Pagos pago : pagos) {
            long dias = pago.getDiasVencido();
            if (pago.getEstado() != Estado.PAGADO) {
                if (dias > 0) {
                    dao.updateEstado(Estado.VENCIDO, pago.getId());
                    nroPagosActualizados += 1;
                }
            }
        }
        response.put("mensaje", "Se actualizo el estado de pagos");
        response.put("nroPagos", nroPagosActualizados);
        return response;
    }

    @Override
    public boolean verifyCuotaByIDStudents(Integer id) {
        Optional<Matricula> matricula = matriculaService.findById(id);
        if (matricula.isEmpty()) {
            throw new NotFoundException("Maricula no existe");
        }
        return false;
    }

    @Override
    public int hasDeudas(Integer idEstudiante) {
        List<Matricula_Pagos> pagos = dao.hasDeudas(idEstudiante);
        if (pagos.isEmpty()) {
            return 0;
        } else {
            return pagos.size();
        }
    }

    @Override
    public void updateFecha(Integer id) {
        dao.updateFechaPago(id);
    }

}

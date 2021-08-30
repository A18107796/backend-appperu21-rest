package com.educacionperu21.apirest.dao;

import com.educacionperu21.apirest.entities.Especializacion;
import com.educacionperu21.apirest.entities.Pago;
import com.educacionperu21.apirest.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PagoDAO extends GenericJPAStatusRepository<Pago, Integer> {

    @Query("SELECT MAX(p.npago) from Pago p")
    Optional<Integer> findLastId();

    @Transactional
    @Modifying
    @Query("update Pago m set m.estado = ?1 where m.id = ?2")
    int updateEstado(Estado estado, Integer id);

    @Query(value ="select COALESCE(sum(a.subtotal),0) from pagos_detalles a left join pagos b on b.id = a.id_pago where b.estado = 'PAGADO'", nativeQuery = true)
    double getGanancias();

    @Query(value ="CALL filtrar_ganancias(?1,?2,?3)", nativeQuery = true)
    double getGananciasBetweenFechas(String estado, String fecha_inicio, String fecha_fin);


    @Query(value = "select pagos.estado, COUNT(*) as 'nveces' from pagos GROUP BY pagos.estado", nativeQuery = true)
    List<Map<String, Integer>> findStadisticPagoStatus();
}

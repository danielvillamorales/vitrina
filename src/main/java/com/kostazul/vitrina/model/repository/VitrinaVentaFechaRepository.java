package com.kostazul.vitrina.model.repository;

import com.kostazul.vitrina.model.entity.VitrinaVentaFecha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VitrinaVentaFechaRepository extends JpaRepository<VitrinaVentaFecha, Long> {

    /**
     * busqueda por cabecera
     * @param cabecera cabeceras
     * @return lista de vitrina venta fecha
     */
    List<VitrinaVentaFecha> findByCabeceraIn(List<Long> cabecera);
}

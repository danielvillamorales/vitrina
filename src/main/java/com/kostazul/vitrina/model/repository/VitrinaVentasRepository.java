package com.kostazul.vitrina.model.repository;

import com.kostazul.vitrina.model.entity.VitrinaVentas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VitrinaVentasRepository extends JpaRepository<VitrinaVentas, Integer> {

    /**
     * busqueda de ventas por bodega y fecha.
     * @param bodega bodega
     * @param fechaInicio fecha inicio
     * @param fechaFin fecha fin
     * @return
     */
    List<VitrinaVentas> findByBodegaAndReferenciaInAndFechaBetween(String bodega,List<String> referencias,
                                                                   Date fechaInicio, Date fechaFin);

}

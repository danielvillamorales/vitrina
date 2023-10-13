package com.kostazul.vitrina.model.repository;

import com.kostazul.vitrina.model.entity.VitrinaCabecera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface VitrinaCabeceraRepository extends JpaRepository<VitrinaCabecera, Integer> {

    //boolean existsByBodegaCodigoAndBetweenFechaInicioAndFechaFin(String bodegaId,Date fechaInicio,Date fechaFin);

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM VitrinaCabecera e " +
            "WHERE e.bodega.codigo = :bodegaCodigo " +
            "AND ((e.fechaInicio BETWEEN :fechaInicio AND :fechaFin) " +
            "OR (e.fechaFin BETWEEN :fechaInicio2 AND :fechaFin2))")
    boolean existsByBodegaCodigoAndFechas(@Param("bodegaCodigo") String bodegaCodigo,
                                          @Param("fechaInicio") Date fechaInicio,
                                          @Param("fechaFin") Date fechaFin,
                                          @Param("fechaInicio2") Date fechaInicio2,
                                          @Param("fechaFin2") Date fechaFin2);

    /**
     * buscar por bodega.
     * @param bodegaCodigo codigo de la bodega.
     * @return lista de cabeceras.
     */
    List<VitrinaCabecera> findByBodegaCodigo(String bodegaCodigo);
}

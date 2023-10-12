package com.kostazul.vitrina.model.repository;

import com.kostazul.vitrina.model.entity.VitrinaCabecera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface VitrinaCabeceraRepository extends JpaRepository<VitrinaCabecera, Integer> {

    //boolean existsByBodegaCodigoAndBetweenFechaInicioAndFechaFin(String bodegaId,Date fechaInicio,Date fechaFin);

    boolean existsByBodegaCodigoAndFechaInicioBetweenOrFechaFinBetween(String bodegaId, Date fechaInicio, Date fechaFin
    , Date fechaInicio2, Date fechaFin2);
}

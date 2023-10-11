package com.kostazul.vitrina.model.repository;

import com.kostazul.vitrina.model.entity.VitrinaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface VitrinaDetalleRepository extends JpaRepository<VitrinaDetalle, Integer> {

    /**
     * buscar vitrinas por cabecera.
     * @param id id de cabecera
     * @return lista de vitrinas
     */
    List<VitrinaDetalle> findByCabeceraId(int id);
}

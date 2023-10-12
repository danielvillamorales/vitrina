package com.kostazul.vitrina.model.repository;

import com.kostazul.vitrina.model.entity.VitrinaImagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VitrinaImagenRepository extends JpaRepository<VitrinaImagen, Integer> {
}

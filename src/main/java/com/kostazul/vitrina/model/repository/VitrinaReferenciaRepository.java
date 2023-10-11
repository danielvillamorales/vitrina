package com.kostazul.vitrina.model.repository;

import com.kostazul.vitrina.model.entity.VitrinaReferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VitrinaReferenciaRepository extends JpaRepository<VitrinaReferencia, String> {

    /**
     * buscar top 20 referencias que empiezan con un string.
     */
    List<VitrinaReferencia> findTop10ByReferenciaStartingWith(String id);
}

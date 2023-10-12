package com.kostazul.vitrina.model.repository;

import com.kostazul.vitrina.model.entity.VitrinaBodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VitrinaBodegaRepository extends JpaRepository<VitrinaBodega, String> {
}

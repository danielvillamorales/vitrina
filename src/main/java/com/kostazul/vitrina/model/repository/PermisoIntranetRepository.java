package com.kostazul.vitrina.model.repository;

import com.kostazul.vitrina.model.entity.PermisosIntranet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermisoIntranetRepository extends JpaRepository<PermisosIntranet, Long> {

    @Query("SELECT MAX(p.permisoPk) FROM PermisosIntranet p")
    Long findMaxPermisoPk();
}

package com.kostazul.vitrina.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "permisos_intranet")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PermisosIntranet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long permisoPk;

    private String codigo;

    private String nombre;

    private String descripcion;

    private String tipo_permiso;

    private String beneficio;

    private Date fecha_inicio;

    private Date fecha_fin;
}

package com.kostazul.vitrina.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "vitrina_cabecera")
public class VitrinaCabecera {

    /**
     *identificador de la cabecera de la vitrina.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * nombre de la cabecera de la vitrina.
     */
    private String nombre;

    /**
     * fecha de inicio de la cabecera de la vitrina.
     */
    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    /**
     * fecha de fin de la cabecera de la vitrina.
     */
    @Column(name = "fecha_fin")
    private Date fechaFin;

    /**
     * bodega de la cabecera de la vitrina.
     */
    @ManyToOne
    @JoinColumn(name = "bodega_id", nullable = false)
    private VitrinaBodega bodega;

    /**
     * detalle de la cabecera.
     */
    @OneToMany(mappedBy = "cabecera")
    private List<VitrinaDetalle> detalle;

}

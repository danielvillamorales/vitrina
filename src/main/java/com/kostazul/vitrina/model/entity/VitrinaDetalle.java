package com.kostazul.vitrina.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="vitrina_detalle")
public class VitrinaDetalle {

    /**
     * identificador del detalle de la vitrina.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * codigo de la referencia de la vitrina.
     */
    @ManyToOne
    @JoinColumn(name = "referencia_id", nullable = false)
    private VitrinaReferencia referencia;

    /**
     * cabecera de la vitrina.
     */
    @ManyToOne
    @JoinColumn(name = "cabecera_id", nullable = false)
    private VitrinaCabecera cabecera;


	/**
     * observaciones de la referencia de la vitrina.
     */
    private String observaciones;

}

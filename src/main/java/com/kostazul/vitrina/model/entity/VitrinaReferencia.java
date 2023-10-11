package com.kostazul.vitrina.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="vitrina_referencia")
public class VitrinaReferencia {

    /**
     * codigo de la referencia de la vitrina.
     */
    @Id
    private String referencia;

    /**
     * descripcion de la referencia de la vitrina.
     */
    private String descripcion;

    /**
     * detalles de una vitrina.
     */
    @OneToMany(mappedBy = "referencia")
    private List<VitrinaDetalle> detalle;

}

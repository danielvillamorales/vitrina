package com.kostazul.vitrina.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VITRINA_VENTAFECHA")
public class VitrinaVentaFecha {


    /**
     * campo id de la vitrina
     */
    @Id
    private Long id;
    /**
     * id de la cabecera de la vitrina
     */
    private Long cabecera;

    /**
     * transaccion de la cabecera de la vitrina
     */
    private String referencia;

    /**
     * descripcion de la referencia
     */
    private String descref;

    /**
     * detalle observacion;
     */
    private String obsdetalle;

    /**
     * producto de la cabecera de la vitrina
     */
    private Long cantidad;
    /**
     * neto
     */
    private Long neto;

}
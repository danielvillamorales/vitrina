package com.kostazul.vitrina.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VitrinaDetalleDto {
    /**
     * identificador del detalle de la vitrina.
     */
    private int id;

    /**
     * codigo de la referencia de la vitrina.
     */
    private VitrinaReferenciaDto referencia;

    /**
     * cabecera de la vitrina.
     */

    private VitrinaCabeceraDto cabecera;


    /**
     * observaciones de la referencia de la vitrina.
     */
    private String observaciones;
}

package com.kostazul.vitrina.web.dto;

import com.kostazul.vitrina.model.entity.VitrinaVentas;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VitrinaDetalleVentaDto {
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

    private long totalNeto;

    private long totalCantidad;

    /***
     * ventas de la referencia de la vitrina.
     */
    private List<VitrinaVentas> ventas;
}

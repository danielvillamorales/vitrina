package com.kostazul.vitrina.web.dto;

import com.kostazul.vitrina.model.entity.VitrinaVentaFecha;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VitrinaCabeceraDetalleVtaDto {

    /**
     * cabecera de la vitrina.
     */

    private VitrinaCabeceraDto cabecera;

    /**
     * total Vitrina
     */
    private long totalNetoVitrina;

    /**
     * total cantidad vitrina
     */

    private long totalCantidadVitrina;

    /**
     * ventas de cada almacen.
     */
    List<VitrinaVentaFecha> detalles;
}

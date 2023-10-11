package com.kostazul.vitrina.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VitrinaBodegaDto {

    /**
     * identificador de la bodega.
     */
    private String codigo;

    /**
     * nombre de la bodega.
     */
    private String nombre;

}

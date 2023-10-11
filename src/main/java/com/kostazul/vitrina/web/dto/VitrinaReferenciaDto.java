package com.kostazul.vitrina.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VitrinaReferenciaDto {

    /**
     * codigo de la referencia de la vitrina.
     */
    private String referencia;

    /**
     * descripcion de la referencia de la vitrina.
     */
    private String descripcion;
}

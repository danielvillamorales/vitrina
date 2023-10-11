package com.kostazul.vitrina.web.dto;

import com.kostazul.vitrina.model.entity.VitrinaBodega;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VitrinaCabeceraDto {
    /**
     * identifcador de la vitrina.
     */
    private int id;
    /**
     * nombre de la cabecera de la vitrina.
     */
    private String nombre;

    /**
     * fecha de inicio de la cabecera de la vitrina.
     */
    private Date fechaInicio;

    /**
     * fecha de fin de la cabecera de la vitrina.
     */
    private Date fechaFin;

    /**
     * bodega de la cabecera de la vitrina.
     */
    private VitrinaBodegaDto bodega;
}

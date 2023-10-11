package com.kostazul.vitrina.utils.factory;

import com.kostazul.vitrina.model.entity.VitrinaBodega;
import com.kostazul.vitrina.web.dto.VitrinaBodegaDto;

public final class BodegaFactory {

    private BodegaFactory() {
    }

    /**
     * convertir entidad a dto.
     * @param bodega entidad.
     * @return dto.
     */
    public static VitrinaBodegaDto convertEntityToDto(VitrinaBodega bodega) {
        return VitrinaBodegaDto.builder()
                .codigo(bodega.getCodigo())
                .nombre(bodega.getNombre())
                .build();
    }
}

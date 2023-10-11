package com.kostazul.vitrina.utils.factory;

import com.kostazul.vitrina.model.entity.VitrinaReferencia;
import com.kostazul.vitrina.web.dto.VitrinaReferenciaDto;

public final class ReferenciaFactory {
    private ReferenciaFactory() {
    }

    public static VitrinaReferenciaDto convertEntityToDto(VitrinaReferencia vitrinaReferencia) {
        return new VitrinaReferenciaDto(vitrinaReferencia.getReferencia(), vitrinaReferencia.getDescripcion());
    }
}

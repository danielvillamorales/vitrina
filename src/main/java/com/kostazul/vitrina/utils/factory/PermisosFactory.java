package com.kostazul.vitrina.utils.factory;

import com.kostazul.vitrina.model.entity.PermisosIntranet;
import com.kostazul.vitrina.web.dto.PermisoDto;

import java.time.OffsetDateTime;
import java.util.Date;

public final class PermisosFactory {

    private PermisosFactory() {
    }

    public static PermisosIntranet createPermisoIntranet(PermisoDto permiso) {
        return PermisosIntranet.builder()
                .permisoPk(permiso.getId())
                .codigo(permiso.getUsuariodepermiso().getUsername())
                .nombre(permiso.getUsuariodepermiso().getFirst_name() + " "
                        + permiso.getUsuariodepermiso().getLast_name())
                .descripcion(permiso.getDescripcion() != null && permiso.getDescripcion().length() > 250
                        ? permiso.getDescripcion().substring(0, 250)
                        : permiso.getDescripcion())
                .fecha_inicio(converStringToDate(permiso.getFechaInicial()))
                .fecha_fin(converStringToDate(permiso.getFechaFinal()))
                .tipo_permiso(permiso.getTipopermiso().getDescripcion())
                .beneficio(permiso.getBeneficio() == null ? "" :permiso.getBeneficio().getNombre() )
                .build();
    }

    private static Date converStringToDate(String fecha) {
        // Convertir el string a OffsetDateTime
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(fecha);

        // Convertir OffsetDateTime a Date
        return Date.from(offsetDateTime.toInstant());
    }
}

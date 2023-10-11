package com.kostazul.vitrina.services;

import com.kostazul.vitrina.model.entity.VitrinaDetalle;
import com.kostazul.vitrina.model.repository.VitrinaDetalleRepository;
import com.kostazul.vitrina.utils.factory.BodegaFactory;
import com.kostazul.vitrina.utils.factory.ReferenciaFactory;
import com.kostazul.vitrina.web.dto.VitrinaCabeceraDto;
import com.kostazul.vitrina.web.dto.VitrinaDetalleDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class VitrinaDetalleServices {

    /**
     * repositorio.
     */
    private final VitrinaDetalleRepository vitrinaDetalleRepository;


    /**
     * guardar detalle.
     * @param vitrinaDetalle
     * @return
     */
    public VitrinaDetalleDto guardarDetalle(VitrinaDetalle vitrinaDetalle) {
        VitrinaDetalle vitrinaDetalle1 = vitrinaDetalleRepository.save(vitrinaDetalle);
        return VitrinaDetalleDto.builder()
                .id(vitrinaDetalle1.getId())
                .referencia(ReferenciaFactory.convertEntityToDto(vitrinaDetalle1.getReferencia()))
                .cabecera(VitrinaCabeceraDto.builder()
                        .id(vitrinaDetalle1.getCabecera().getId())
                        .nombre(vitrinaDetalle1.getCabecera().getNombre())
                        .fechaInicio(vitrinaDetalle1.getCabecera().getFechaInicio())
                        .fechaFin(vitrinaDetalle1.getCabecera().getFechaFin())
                        .bodega(BodegaFactory.convertEntityToDto(vitrinaDetalle1.getCabecera().getBodega()))
                        .build())
                .observaciones(vitrinaDetalle1.getObservaciones())
                .build();
    }

    /**
     * listar detalle.
     * @param id
     * @return lista de detalle.
     */
    public List<VitrinaDetalleDto> listarVitrinaDetalle(int id){
        return vitrinaDetalleRepository.findByCabeceraId(id).stream().map(
                vitrinaDetalle1 -> VitrinaDetalleDto.builder()
                        .id(vitrinaDetalle1.getId())
                        .referencia(ReferenciaFactory.convertEntityToDto(vitrinaDetalle1.getReferencia()))
                        .cabecera(VitrinaCabeceraDto.builder()
                                .id(vitrinaDetalle1.getCabecera().getId())
                                .nombre(vitrinaDetalle1.getCabecera().getNombre())
                                .fechaInicio(vitrinaDetalle1.getCabecera().getFechaInicio())
                                .fechaFin(vitrinaDetalle1.getCabecera().getFechaFin())
                                .bodega(BodegaFactory.convertEntityToDto(vitrinaDetalle1.getCabecera().getBodega()))
                                .build())
                        .observaciones(vitrinaDetalle1.getObservaciones())
                        .build()
        ).collect(Collectors.toList());
    }
}

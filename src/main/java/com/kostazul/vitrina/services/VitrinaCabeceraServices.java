package com.kostazul.vitrina.services;

import com.kostazul.vitrina.model.entity.VitrinaCabecera;
import com.kostazul.vitrina.model.repository.VitrinaCabeceraRepository;
import com.kostazul.vitrina.utils.factory.BodegaFactory;
import com.kostazul.vitrina.web.dto.VitrinaCabeceraDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class VitrinaCabeceraServices {

    /**
     * repositorio.
     * */
    private final VitrinaCabeceraRepository vitrinaCabeceraRepository;

    /**
     * guardar cabecera.
     * @param vitrinaCabecera cabecera.
     * @return cabecera.
     */
    public VitrinaCabecera guardarCabecera(final VitrinaCabecera vitrinaCabecera) {
        VitrinaCabecera vitrinaCabecera1 = vitrinaCabeceraRepository.save(vitrinaCabecera);
        log.info("vitrina cabecera creada: {}", vitrinaCabecera1);
        return vitrinaCabecera1;
    }

    /**
     * listar cabecera.
     * @return lista de cabecera.
     */
    public List<VitrinaCabeceraDto> listarCabecera(){
        return vitrinaCabeceraRepository.findAll().stream()
                .map(vitrinaCabecera -> VitrinaCabeceraDto.builder()
                        .id(vitrinaCabecera.getId())
                        .nombre(vitrinaCabecera.getNombre())
                        .fechaInicio(vitrinaCabecera.getFechaInicio())
                        .fechaFin(vitrinaCabecera.getFechaFin())
                        .bodega(BodegaFactory.convertEntityToDto(vitrinaCabecera.getBodega()))
                        .build()).collect(Collectors.toList());
    }

    /**
     * listar cabecera.
     * @return lista de cabecera.
     */
    public VitrinaCabeceraDto listarCabecera(final int id){
        return vitrinaCabeceraRepository.findById(id).map(vitrinaCabecera -> VitrinaCabeceraDto.builder()
                        .id(vitrinaCabecera.getId())
                        .nombre(vitrinaCabecera.getNombre())
                        .fechaInicio(vitrinaCabecera.getFechaInicio())
                        .fechaFin(vitrinaCabecera.getFechaFin())
                        .bodega(BodegaFactory.convertEntityToDto(vitrinaCabecera.getBodega()))
                        .build()).orElseThrow(() -> new RuntimeException("No se encontro la cabecera"));
    }

}

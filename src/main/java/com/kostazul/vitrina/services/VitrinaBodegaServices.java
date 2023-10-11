package com.kostazul.vitrina.services;

import com.kostazul.vitrina.model.entity.VitrinaBodega;
import com.kostazul.vitrina.model.repository.VitrinaBodegaRepository;
import com.kostazul.vitrina.web.dto.VitrinaBodegaDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class VitrinaBodegaServices {

    private final VitrinaBodegaRepository vitrinaBodegaRepository;


    /**
     * creacion de una bodega.
     * @param vitrinaBodega vitrina bodega.
     * @return vitrina bodega.
     */
    public VitrinaBodega createVitrinaBodega(final VitrinaBodega vitrinaBodega) {
        VitrinaBodega vitrinaBodega1 = vitrinaBodegaRepository.save(vitrinaBodega);
      	log.info("vitrina bodega creada: {}", vitrinaBodega1);
        return vitrinaBodega1;
    }

    /**
     * listar todas las vitrinas.
     */
    public List<VitrinaBodegaDto> listVitrinaBodega(){
        List<VitrinaBodegaDto> bodegasDto = vitrinaBodegaRepository.findAll().stream()
                .map(bodega -> VitrinaBodegaDto.builder()
                        .codigo(bodega.getCodigo())
                        .nombre(bodega.getNombre())
                        .build()).collect(Collectors.toList());
        log.info(bodegasDto.toString());
        return bodegasDto;
    }


    /**
     * borrar una bodega.
     */
    public Boolean deleteVitrinaBodega(String id) {
        if (vitrinaBodegaRepository.existsById(id)) {
            vitrinaBodegaRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

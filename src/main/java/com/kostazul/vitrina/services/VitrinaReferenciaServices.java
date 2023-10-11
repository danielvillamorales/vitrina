package com.kostazul.vitrina.services;

import com.kostazul.vitrina.model.repository.VitrinaReferenciaRepository;
import com.kostazul.vitrina.web.dto.VitrinaReferenciaDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class VitrinaReferenciaServices {

    /**
     * repositorio.
     */
    private final VitrinaReferenciaRepository vitrinaReferenciaRepository;


    /**
     * buscar top 20 referencias que empiezan con un string.
     */
    public List<VitrinaReferenciaDto> buscarTop(String id) {
        log.info("buscar" + id);
        return vitrinaReferenciaRepository.findTop10ByReferenciaStartingWith(id)
                .stream()
                .map(vitrinaReferencia -> new  VitrinaReferenciaDto(
                        vitrinaReferencia.getReferencia(),
                        vitrinaReferencia.getDescripcion()
                )).collect(Collectors.toList());
    }

}

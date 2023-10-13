package com.kostazul.vitrina.web.controller;


import com.kostazul.vitrina.services.VitrinaReferenciaServices;
import com.kostazul.vitrina.web.dto.VitrinaReferenciaDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vitrina/referencia")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = {"http://psv.kostazul.com:9090", "http://localhost:4200", "http://192.168.0.13:9090"})
public class VitrinaReferenciaController {

    /**
     * servicio de vitrina referencia.
     */
    private final VitrinaReferenciaServices vitrinaReferenciaServices;


    /**
     * Listar Top 20 referencias que empiezan con un string.
     * @param id id de la referencia.
     * @return lista de referencias.
     */
    @GetMapping
    public ResponseEntity<List<VitrinaReferenciaDto>> buscarTop(@RequestParam("id") String id) {
        log.info("buscar" + id);
        List<VitrinaReferenciaDto> vitrinaReferenciaDtoList = vitrinaReferenciaServices.buscarTop(id);
        log.info("encontradas" + vitrinaReferenciaDtoList);
        return new ResponseEntity<>(vitrinaReferenciaDtoList, HttpStatus.OK);
    }
}

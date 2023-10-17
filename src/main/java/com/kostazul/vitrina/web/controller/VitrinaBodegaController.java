package com.kostazul.vitrina.web.controller;

import com.kostazul.vitrina.model.entity.VitrinaBodega;
import com.kostazul.vitrina.services.VitrinaBodegaServices;
import com.kostazul.vitrina.web.dto.VitrinaBodegaDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vitrina/bodega")
@AllArgsConstructor
@CrossOrigin(origins = {"http://psv.kostazul.com:9090",
        "http://localhost:4200",
        "http://192.168.0.13:9090",
        "https://psv.kostazul.com:9090",
        "https://localhost:4200",
        "https://192.168.0.13:9090"})
@Slf4j
public class VitrinaBodegaController {

    /**
     * vitrina bodega services.
     */
	private final VitrinaBodegaServices vitrinaBodegaServices;
    @PostMapping
    public ResponseEntity<VitrinaBodega> createVitrinaBodega(@RequestBody VitrinaBodega vitrinaBodega) {
		return new ResponseEntity<>(vitrinaBodegaServices.createVitrinaBodega(vitrinaBodega), HttpStatus.CREATED);
    }

    /**
     * listar todas las bodegas.
     * @return lista de bodegas
     */
    @GetMapping
    public ResponseEntity<List<VitrinaBodegaDto>> listVitrinaBodega() {
        return new ResponseEntity<>(vitrinaBodegaServices.listVitrinaBodega(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteVitrinaBodega(@RequestParam("id") String id) {
        log.info("borrar" + id);
    	return new ResponseEntity<>(vitrinaBodegaServices.deleteVitrinaBodega(id), HttpStatus.OK);
    }
}

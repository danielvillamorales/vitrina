package com.kostazul.vitrina.web.controller;

import com.kostazul.vitrina.model.entity.VitrinaDetalle;
import com.kostazul.vitrina.services.VitrinaDetalleServices;
import com.kostazul.vitrina.web.dto.VitrinaCabeceraDto;
import com.kostazul.vitrina.web.dto.VitrinaDetalleDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vitrina/detalle")
@AllArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class VitrinaDetalleController {

    /**
     * servicio.
     */
    private final VitrinaDetalleServices vitrinaDetalleServices;

    /**
     * guardar detalle.
     * @param vitrinaDetalle vitrina detalla
     * @return vitrina
     */
    @PostMapping
    public ResponseEntity<VitrinaDetalleDto> guardarDetalle(@RequestBody VitrinaDetalle vitrinaDetalle){
        log.info("guardar detalle", vitrinaDetalle);
        VitrinaDetalleDto vitrinaDetalle1 = vitrinaDetalleServices.guardarDetalle(vitrinaDetalle);
        return new ResponseEntity<>(vitrinaDetalle1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VitrinaDetalleDto>> listarVitrinaDetalle(@RequestParam("id") int id){
        log.info("listar detalle");
        List<VitrinaDetalleDto> vitrinaDetalleList = vitrinaDetalleServices.listarVitrinaDetalle(id);
        return new ResponseEntity<>(vitrinaDetalleList, HttpStatus.OK);
    }



}

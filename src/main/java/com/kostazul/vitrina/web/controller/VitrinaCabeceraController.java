package com.kostazul.vitrina.web.controller;

import com.kostazul.vitrina.model.entity.VitrinaCabecera;
import com.kostazul.vitrina.services.VitrinaCabeceraServices;
import com.kostazul.vitrina.web.dto.VitrinaCabeceraDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vitrina/cabecera")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class VitrinaCabeceraController {

    /**
     * servicio de cabecera.
     */
    private final VitrinaCabeceraServices vitrinaCabeceraServices;


    /**
     * guardar cabecera.
     * @param vitrinaCabecera vitrina cabecera.
     * @return cabecera.
     */
    @PostMapping
    public ResponseEntity<VitrinaCabecera> guardarCabecera(@RequestBody VitrinaCabecera vitrinaCabecera){
        log.info("guardar cabecera", vitrinaCabecera);
        return new ResponseEntity<>(vitrinaCabeceraServices.guardarCabecera(vitrinaCabecera), HttpStatus.CREATED);
    }

    /**
     * listar todas las vitrinas.
     * @return
     */
    @GetMapping
    public ResponseEntity<List<VitrinaCabeceraDto>> listarCabecera(){
        log.info("listar cabecera");
        List<VitrinaCabeceraDto> vitrinaCabeceraList = vitrinaCabeceraServices.listarCabecera();
        return new ResponseEntity<>(vitrinaCabeceraList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VitrinaCabeceraDto> listarCabecera(@PathVariable("id") int id){
        log.info("listar cabecera");
        VitrinaCabeceraDto vitrinaCabeceraList = vitrinaCabeceraServices.listarCabecera(id);
        return new ResponseEntity<>(vitrinaCabeceraList, HttpStatus.OK);
    }
}

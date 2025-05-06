package com.kostazul.vitrina.web.controller;

import com.kostazul.vitrina.model.entity.VitrinaDetalle;
import com.kostazul.vitrina.services.VitrinaDetalleServices;
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
@CrossOrigin(origins = {"http://psv.kostazul.com:9090",
        "http://localhost:4200",
        "http://192.168.0.13:9090",
        "https://psv.kostazul.com:9090",
        "https://localhost:4200",
        "https://192.168.0.13:9090"})
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
        VitrinaDetalleDto vitrinaDetalle1 = vitrinaDetalleServices.guardarDetalle(vitrinaDetalle);
        return new ResponseEntity<>(vitrinaDetalle1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VitrinaDetalleDto>> listarVitrinaDetalle(@RequestParam("id") int id){
        List<VitrinaDetalleDto> vitrinaDetalleList = vitrinaDetalleServices.listarVitrinaDetalle(id);
        return new ResponseEntity<>(vitrinaDetalleList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarDetalle(@PathVariable("id") int id){
        return new ResponseEntity<>(vitrinaDetalleServices.eliminarDetalle(id), HttpStatus.OK);
    }
}

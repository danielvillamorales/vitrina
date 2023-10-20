package com.kostazul.vitrina.web.controller;

import com.kostazul.vitrina.model.entity.VitrinaVentas;
import com.kostazul.vitrina.services.VitrinaVentasServices;
import com.kostazul.vitrina.web.dto.VitrinaCabeceraDetalleVtaDto;
import com.kostazul.vitrina.web.dto.VitrinaDetalleVentaDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/vitrina/ventas")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = {"http://psv.kostazul.com:9090",
        "http://localhost:4200",
        "http://192.168.0.13:9090",
        "https://psv.kostazul.com:9090",
        "https://localhost:4200",
        "https://192.168.0.13:9090"})
public class VitrinaVentasController {

    private final VitrinaVentasServices vitrinaVentasServices;

    @GetMapping
    public List<VitrinaDetalleVentaDto> getByVitrina(@RequestParam int id){
        log.info("id de la vitrina: " , id);
        return vitrinaVentasServices.getVentasByVitrina(id);
    }

    /**
     * obtiene las ventas por fecha
     * @param fechaInicio
     * @param fechaFin
     * @return
     */
    @GetMapping("/fecha")
    public List<VitrinaCabeceraDetalleVtaDto> getByFechas(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                              Date fechaInicio,
                                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                          Date fechaFin){
        log.info("id de la vitrina: ");
        return vitrinaVentasServices.getVentasByFecha(fechaInicio, fechaFin);
    }
}

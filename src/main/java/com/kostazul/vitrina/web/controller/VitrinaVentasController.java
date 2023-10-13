package com.kostazul.vitrina.web.controller;

import com.kostazul.vitrina.model.entity.VitrinaVentas;
import com.kostazul.vitrina.services.VitrinaVentasServices;
import com.kostazul.vitrina.web.dto.VitrinaDetalleVentaDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/vitrina/ventas")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class VitrinaVentasController {

    private final VitrinaVentasServices vitrinaVentasServices;

    @GetMapping
    public List<VitrinaDetalleVentaDto> getByVitrina(@RequestParam int id){
        log.info("id de la vitrina: " , id);
        return vitrinaVentasServices.getVentasByVitrina(id);
    }
}

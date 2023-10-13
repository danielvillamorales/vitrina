package com.kostazul.vitrina.web.controller;

import com.kostazul.vitrina.model.entity.VitrinaCabecera;
import com.kostazul.vitrina.model.entity.VitrinaImagen;
import com.kostazul.vitrina.services.VitrinaCabeceraServices;
import com.kostazul.vitrina.web.dto.VitrinaCabeceraDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vitrina/cabecera")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class VitrinaCabeceraController {

    /**
     * directorio de imagenes.
     */
    private static final String UPLOAD_DIRECTORY = "/u/vitrinas/dist/vitrinas/images/cargueImagenes/";
    private static final String UPLOAD_DIRECTORY2 = "/cargueImagenes/";
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarCabecera(@PathVariable("id") int id){
        log.info("eliminar cabecera");
        return new ResponseEntity<>(vitrinaCabeceraServices.eliminarCabecera(id), HttpStatus.OK);
    }

    @PostMapping("/image/{id}")
    public ResponseEntity<Boolean> guardarImagenCabecera(@PathVariable("id") int id,
                                                        @RequestParam("images") MultipartFile[] files){
        log.info("guardar imagen cabecera");
        if (files.length == 0){
            throw new RuntimeException("No se ha han ninguna imagen");
        }
        try {
            List<VitrinaImagen> paths = new ArrayList<>();
            for (MultipartFile file : files) {
                if(file.isEmpty()){
                    throw new RuntimeException("No se ha cargado ninguna imagen");
                }
                String fullPath = UPLOAD_DIRECTORY + Integer.toString(id) + "/";
                Path path = Paths.get(fullPath +   file.getOriginalFilename());
                if (!Files.exists(path)) {
                    Files.createDirectories(path.getParent());
                }
                Files.write(path, file.getBytes());
                paths.add(VitrinaImagen.builder()
                                .nombre( UPLOAD_DIRECTORY2+Integer.toString(id) + "/"+file.getOriginalFilename())
                                .build());
            }
            if (!vitrinaCabeceraServices.updateListImages(paths, id)){
                throw new RuntimeException("No se ha podido guardar todas las imagenes");
            }
        } catch (Exception e){
            throw new RuntimeException(e.toString());
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/bodega/{bodega}")
    public ResponseEntity<List<VitrinaCabeceraDto>> listarCabecera(@PathVariable("bodega") String bodega){
        log.info("listar cabecera");
        List<VitrinaCabeceraDto> vitrinaCabeceraList = vitrinaCabeceraServices.findbyBodega(bodega);
        return new ResponseEntity<>(vitrinaCabeceraList, HttpStatus.OK);
    }
}

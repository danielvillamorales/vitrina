package com.kostazul.vitrina.services;

import com.kostazul.vitrina.model.entity.VitrinaCabecera;
import com.kostazul.vitrina.model.entity.VitrinaImagen;
import com.kostazul.vitrina.model.repository.VitrinaCabeceraRepository;
import com.kostazul.vitrina.model.repository.VitrinaImagenRepository;
import com.kostazul.vitrina.utils.factory.BodegaFactory;
import com.kostazul.vitrina.web.dto.VitrinaCabeceraDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class VitrinaCabeceraServices {

    /**
     * repositorio.
     * */
    private final VitrinaCabeceraRepository vitrinaCabeceraRepository;

    private final VitrinaImagenRepository vitrinaImagenRepository;

    /**
     * guardar cabecera.
     * @param vitrinaCabecera cabecera.
     * @return cabecera.
     */
    public VitrinaCabecera guardarCabecera(final VitrinaCabecera vitrinaCabecera) {
        if (vitrinaCabeceraRepository.existsByBodegaCodigoAndFechas(
                vitrinaCabecera.getBodega().getCodigo(),
                vitrinaCabecera.getFechaInicio(),
                vitrinaCabecera.getFechaFin(),
                vitrinaCabecera.getFechaInicio(),
                vitrinaCabecera.getFechaFin()
        )) {
            throw new RuntimeException("Ya existe una vitrina para la bodega en ese rango de fechas");
        }
        VitrinaCabecera vitrinaCabecera1 = vitrinaCabeceraRepository.save(vitrinaCabecera);
        log.info("vitrina cabecera creada: {}", vitrinaCabecera1);
        return vitrinaCabecera1;
    }

    /**
     * listar cabecera.
     * @return lista de cabecera.
     */
    public Page<VitrinaCabecera> listarCabecera(Pageable pageable){
        return vitrinaCabeceraRepository.findAll(pageable);
    }

    /**
     * listar cabecera.
     * @return lista de cabecera.
     */
    public VitrinaCabeceraDto listarCabecera(final int id){
        return vitrinaCabeceraRepository.findById(id).map(vitrinaCabecera -> VitrinaCabeceraDto.builder()
                        .id(vitrinaCabecera.getId())
                        .nombre(vitrinaCabecera.getNombre())
                        .fechaInicio(vitrinaCabecera.getFechaInicio())
                        .fechaFin(vitrinaCabecera.getFechaFin())
                        .bodega(BodegaFactory.convertEntityToDto(vitrinaCabecera.getBodega()))
                        .imagenes(vitrinaCabecera.getImagenes().stream()
                            .map(VitrinaImagen::getNombre).toList())
                        .build()).orElseThrow(() -> new RuntimeException("No se encontro la el objeto solicitado"));
    }

    /**
     * eliminar cabecera.
     * @param id id a eliminar
     * @return bolleano
     */
    public Boolean eliminarCabecera(final int id){
        vitrinaCabeceraRepository.deleteById(id);
        return true;
    }

    /**
     * actualizar lista de imagenes.
     * @param listImages lista de imagenes
     * @param id id de la vitrina cabecera
     * @return verdadero
     */
    public boolean updateListImages(final List<VitrinaImagen> listImages, final int id){
        try {
        VitrinaCabecera vitrina = vitrinaCabeceraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro la vitrina"));
        List<VitrinaImagen> imagenes = listImages.stream().map(vitrinaImagen ->
                setImageToVitrinaCabecera(vitrina, vitrinaImagen)).toList();
        List<VitrinaImagen> vitrinaImagenUpdate = vitrinaImagenRepository.saveAll(imagenes);
            return vitrinaImagenUpdate.size() == listImages.size();
        } catch (Exception e){
            throw new RuntimeException("No se encontro la cabecera");
        }
    }

    /**
     * actualizar cabecera.
     * @param vitrinaCabecera cabecera.
     * @param vitrinaImagen lista de imagenes
     */
    private VitrinaImagen setImageToVitrinaCabecera(final VitrinaCabecera vitrinaCabecera,
                                                    final VitrinaImagen vitrinaImagen){
        vitrinaImagen.setVitrinaCabecera(vitrinaCabecera);
        return vitrinaImagen;
    }

    public List<VitrinaCabeceraDto> findbyBodega(final String bodega){
        List<VitrinaCabecera> vitrinaCabeceraDtoList = vitrinaCabeceraRepository.findByBodegaCodigo(bodega);
        if (vitrinaCabeceraDtoList.isEmpty()){
            throw new RuntimeException("No se encontro la cabecera");
        }
        return vitrinaCabeceraDtoList.stream().map(vitrinaCabecera -> VitrinaCabeceraDto.builder()
                .id(vitrinaCabecera.getId())
                .nombre(vitrinaCabecera.getNombre())
                .fechaInicio(vitrinaCabecera.getFechaInicio())
                .fechaFin(vitrinaCabecera.getFechaFin())
                .bodega(BodegaFactory.convertEntityToDto(vitrinaCabecera.getBodega()))
                .imagenes(vitrinaCabecera.getImagenes().stream()
                        .map(VitrinaImagen::getNombre).toList())
                .build()).toList();
    }


}

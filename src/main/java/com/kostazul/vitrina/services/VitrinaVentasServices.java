package com.kostazul.vitrina.services;

import com.kostazul.vitrina.model.entity.*;
import com.kostazul.vitrina.model.repository.VitrinaCabeceraRepository;
import com.kostazul.vitrina.model.repository.VitrinaVentaFechaRepository;
import com.kostazul.vitrina.model.repository.VitrinaVentasRepository;
import com.kostazul.vitrina.utils.factory.BodegaFactory;
import com.kostazul.vitrina.utils.factory.ReferenciaFactory;
import com.kostazul.vitrina.web.dto.VitrinaCabeceraDetalleVtaDto;
import com.kostazul.vitrina.web.dto.VitrinaCabeceraDto;
import com.kostazul.vitrina.web.dto.VitrinaDetalleVentaDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class VitrinaVentasServices {

    private final VitrinaVentasRepository vitrinaVentasRepository;

    private final VitrinaCabeceraRepository vitrinaCabeceraRepository;

    private final VitrinaVentaFechaRepository vitrinaVentaFechaRepository;


    /**
     * convierte las referencias en string
     * @param vitrina vitrina cabecera
     * @return lista de referencias
     */

    private List<String> getReferenciasByVitrina(final VitrinaCabecera vitrina){
        return vitrina.getDetalle().stream().map(
                detalle -> detalle.getReferencia().getReferencia()
        ).collect(Collectors.toList());

    }

    /**
     * build de la vitrina detalle venta dto
     * @param vitrinaDetalle1 vitrina detalle
     * @param vitrinaVentasList lista de ventas
     * @return vitrina detalle venta dto
     */
    private VitrinaDetalleVentaDto buildDto(final VitrinaDetalle vitrinaDetalle1,
                                            final List<VitrinaVentas> vitrinaVentasList){
        List<VitrinaVentas> ventas = vitrinaVentasList.stream().filter(
                vitrinaVentas -> vitrinaVentas.getReferencia().equals(vitrinaDetalle1.getReferencia().getReferencia())
        ).collect(Collectors.toList());
        long total = ventas.stream().mapToLong(VitrinaVentas::getNeto).sum();
        long cantidad = ventas.stream().mapToLong(VitrinaVentas::getCantidad).sum();
        return VitrinaDetalleVentaDto.builder()
                .id(vitrinaDetalle1.getId())
                .referencia(ReferenciaFactory.convertEntityToDto(vitrinaDetalle1.getReferencia()))
                .cabecera(VitrinaCabeceraDto.builder()
                        .id(vitrinaDetalle1.getCabecera().getId())
                        .nombre(vitrinaDetalle1.getCabecera().getNombre())
                        .fechaInicio(vitrinaDetalle1.getCabecera().getFechaInicio())
                        .fechaFin(vitrinaDetalle1.getCabecera().getFechaFin())
                        .bodega(BodegaFactory.convertEntityToDto(vitrinaDetalle1.getCabecera().getBodega()))
                        .build())
                .ventas(ventas)
                .totalCantidad(cantidad)
                .totalNeto(total)
                .observaciones(vitrinaDetalle1.getObservaciones())
                .build();
    }

    /**
     * obtiene las ventas por vitrina
     * @param id id de la vitrina
     * @return lista de ventas
     */
    public List<VitrinaDetalleVentaDto> getVentasByVitrina(int id){
        VitrinaCabecera vitrina = vitrinaCabeceraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro la cabecera: " + id));
        List<VitrinaVentas> vitrinaVentasList = vitrinaVentasRepository
                .findByBodegaAndReferenciaInAndFechaBetween(vitrina.getBodega().getCodigo(),
                        getReferenciasByVitrina(vitrina), vitrina.getFechaInicio(), vitrina.getFechaFin());
        if (vitrinaVentasList.isEmpty()){
            throw new RuntimeException("No se encontraron ventas para la vitrina en el rango de fechas");
        }
        return vitrina.getDetalle().stream().map(
                vitrinaDetalle1 -> buildDto(vitrinaDetalle1, vitrinaVentasList)
            ).collect(Collectors.toList());
    }

    /**
     * convert entuty to dto
     * @param vitrinaCabecera
     * @return dto
     */
    private static VitrinaCabeceraDto vitrinaCabeceraToDto(VitrinaCabecera vitrinaCabecera){
        return VitrinaCabeceraDto.builder()
                .id(vitrinaCabecera.getId())
                .nombre(vitrinaCabecera.getNombre())
                .fechaInicio(vitrinaCabecera.getFechaInicio())
                .fechaFin(vitrinaCabecera.getFechaFin())
                .bodega(BodegaFactory.convertEntityToDto(vitrinaCabecera.getBodega()))
                .imagenes(vitrinaCabecera.getImagenes().stream()
                        .map(VitrinaImagen::getNombre).collect(Collectors.toList()))
                .build();
    }


    /**
     * conversion
     * @param ventas
     * @param vitrina
     * @return
     */
    private VitrinaCabeceraDetalleVtaDto transformDataVenta(final List<VitrinaVentaFecha> ventas,
                                                                  final VitrinaCabecera vitrina ){
        List<VitrinaVentaFecha> ventasFechas = ventas.stream().filter(venta -> venta.getCabecera()
                        .equals(vitrina.getId()))
                .collect(Collectors.toList());
        long totalCantidad = ventasFechas.stream().mapToLong(VitrinaVentaFecha::getCantidad).sum();
        long totalNeto = ventasFechas.stream().mapToLong(VitrinaVentaFecha::getNeto).sum();

        return VitrinaCabeceraDetalleVtaDto.builder()
                .cabecera(vitrinaCabeceraToDto(vitrina))
                .totalCantidadVitrina(totalCantidad)
                .totalNetoVitrina(totalNeto)
                .detalles(ventasFechas)
                .build();
    }

    /**
     * obtiene las ventas por fecha
     * @param fechaInicial fecha inicial
     * @param fechaFinal fecha final
     * @return lista de ventas
     */
    public List<VitrinaCabeceraDetalleVtaDto> getVentasByFecha(final Date fechaInicial, final Date fechaFinal){
        List<VitrinaCabecera> vitrinas = vitrinaCabeceraRepository.findByFechaInicioBetweenOrFechaFinBetween(
                fechaInicial, fechaFinal, fechaInicial, fechaFinal);
        if (vitrinas.isEmpty()){
            throw new RuntimeException("no hay vitrinas en el rango de fechas");
        }
        List<VitrinaVentaFecha> ventasFechas = vitrinaVentaFechaRepository.findByCabeceraIn(
                vitrinas.stream().map(VitrinaCabecera::getId)
                        .collect(Collectors.toList()));
        if (ventasFechas.isEmpty()){
            return vitrinas.stream().map(
                    vitrinaCabecera -> VitrinaCabeceraDetalleVtaDto.builder()
                            .cabecera(vitrinaCabeceraToDto(vitrinaCabecera))
                            .totalCantidadVitrina(0L)
                            .totalNetoVitrina(0L)
                            .build()
            ).collect(Collectors.toList());
        }
        return vitrinas.stream().map(vitrina -> transformDataVenta(ventasFechas, vitrina))
                .collect(Collectors.toList());
    }
}

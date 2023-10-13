package com.kostazul.vitrina.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "vitrina_ventas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VitrinaVentas {

    @Id
    private int id ;

    @Column(name = "ms_nit")
    private int nit;

    private String cliente;

    @Column(name = "fechadoc")
    private Date fecha;

    @Column(name = "ms_transaccion")
    private String transaccion;

    private int documento;

    @Column(name = "md_bodega")
    private String bodega;

    private String referencia;

    private String producto;

    private String talla;

    private long cantidad;

    private long neto;
}

package com.kostazul.vitrina.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="vitrinas_bodega")
public class VitrinaBodega {

    /**
     * identificador de la bodega.
     */
    @Id
    @Column(name="codigo", unique = true)
    private String codigo;

    /**
     * nombre de la bodega.
     */
    @Column(name="nombre")
    private String nombre;

    /**
     * lista de vitrinas de la bodega.
     */
    @JsonIgnore
    @Column(name="vitrinas")
    @OneToMany(mappedBy = "bodega")
    private List<VitrinaCabecera> vitrinas;

}

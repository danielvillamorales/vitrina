package com.kostazul.vitrina.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class VitrinaImagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "vitrina_cabecera_id")
    private VitrinaCabecera vitrinaCabecera;

}

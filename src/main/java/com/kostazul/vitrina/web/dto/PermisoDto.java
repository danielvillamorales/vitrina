package com.kostazul.vitrina.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PermisoDto {

        private Long id;
        private UsuarioDto usuariodecreacion;
        private UsuarioDto usuariodepermiso;
        private UsuarioDto usuarioaprobacion;
        private TipoPermisoDto tipopermiso;
        private Beneficio beneficio;
        private String fechaaprobacion;
        private String fechaInicial;
        private String fechaFinal;
        private String salida;
        private String reingreso;
        private String descripcion;
        private Integer estado;
        private String fechacreacion;

        @Data
        @AllArgsConstructor
        @Builder
        @NoArgsConstructor
        public static class UsuarioDto {
            private Long id;
            private String username;
            private String first_name;
            private String last_name;
            private String email;
        }

        @Data
        @AllArgsConstructor
        @Builder
        @NoArgsConstructor
        public static class TipoPermisoDto {
            private Long id;
            private String descripcion;
        }

    @Data
    public static class Beneficio {
        private String nombre;
        private String detalle;
    }
}

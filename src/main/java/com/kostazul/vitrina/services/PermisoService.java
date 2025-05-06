package com.kostazul.vitrina.services;

import com.kostazul.vitrina.model.entity.PermisosIntranet;
import com.kostazul.vitrina.model.repository.PermisoIntranetRepository;
import com.kostazul.vitrina.utils.factory.PermisosFactory;
import com.kostazul.vitrina.utils.feign.PermisosApiClient;
import com.kostazul.vitrina.web.dto.PermisoDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class PermisoService {

    private final PermisoIntranetRepository permisoIntranetRepository;
    private final PermisosApiClient permisosApiClient;

    @Scheduled(cron = "0 10 * * * *")
    public void buscarpermisos() {
        Long ultimoPermiso = permisoIntranetRepository.findMaxPermisoPk();
        ultimoPermiso = (ultimoPermiso == null) ? 12000L : ultimoPermiso;
        List<PermisoDto> listaPermisos =  buscarPermisosApi(ultimoPermiso);
        if (listaPermisos.isEmpty()) {
            log.info("No hay permisos nuevos");
            return;
        }
        guardarPermisos(listaPermisos);
        log.info("Ultimo permiso: {}", listaPermisos);
    }

    private List<PermisoDto> buscarPermisosApi(Long ultimoPermiso) {
        return permisosApiClient.getPermisos(ultimoPermiso);
    }

    private void guardarPermisos(List<PermisoDto> listaPermisos) {
        List<PermisosIntranet> permisos = permisoIntranetRepository.saveAll(
                listaPermisos.stream()
                        .map(PermisosFactory::createPermisoIntranet)
                        .toList()
        );
        if (!permisos.isEmpty()) {
            log.info("Se guardaron {} permisos", permisos.size());
        } else {
            log.info("No se guardaron permisos");
        }
    }

}

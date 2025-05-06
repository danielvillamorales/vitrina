package com.kostazul.vitrina.utils.feign;

import com.kostazul.vitrina.utils.config.InsecureFeignConfig;
import com.kostazul.vitrina.web.dto.PermisoDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "permisosApi", url = "https://intranet.kostazul.com/api", configuration = InsecureFeignConfig.class)
public interface PermisosApiClient {

    @GetMapping("/permisos/")
    List<PermisoDto> getPermisos(@RequestParam("ultimo_id") Long ultimoId);
}

package com.kostazul.vitrina.utils.config;

import feign.Client;
import org.springframework.context.annotation.Bean;

import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class InsecureFeignConfig {

    @Bean
    public Client feignClient() throws Exception {
        // TrustManager que no valida ningún certificado
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                    public void checkServerTrusted(X509Certificate[] certs, String authType) { }
                }
        };

        // Inicializar contexto SSL con el TrustManager inseguro
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new SecureRandom());

        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        HostnameVerifier hostnameVerifier = (hostname, session) -> true;

        // Devolver el Feign Client inseguro
        return new Client.Default(sslSocketFactory, hostnameVerifier);
    }
}

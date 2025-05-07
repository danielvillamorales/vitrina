package com.kostazul.vitrina.utils.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class SecretKeyInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Secret-Key", "t3cn0l0g14K05t4zul0147.*");
    }
}

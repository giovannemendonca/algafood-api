package com.algaworks.algafood.core.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiDeprecationHandler implements HandlerInterceptor {

    // esse método é executado antes de qualquer requisição que chega na API
    // e se a URI da requisição começar com /v1/ então adiciona um header na resposta para informar
    // que essa versão da API está depreciada e deixará de existir a partir de 01/01/2024

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getRequestURI().startsWith("/v1/")) {
            response.addHeader("X-AlgaFood-Deprecated",
                    "Essa versão da API está depreciada e deixará de existir a partir de 01/01/2024. Use a versão mais atual.");
        }

        return true;

    }
}

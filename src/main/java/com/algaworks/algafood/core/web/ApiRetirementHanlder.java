package com.algaworks.algafood.core.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiRetirementHanlder implements HandlerInterceptor {

    // esse método é executado antes de qualquer requisição que chega na API
    // e se a URI da requisição começar com /v1/ então retorna um status 410 (GONE)
    // para informar que essa versão da API foi descontinuada e não existe mais.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().startsWith("/v1/")) {
            response.setStatus(HttpStatus.GONE.value());
        }
        return false;
    }
}

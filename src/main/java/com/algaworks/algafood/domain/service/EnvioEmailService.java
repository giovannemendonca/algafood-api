package com.algaworks.algafood.domain.service;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Set;

public interface EnvioEmailService {

    void enviar(Mensagem mensagem);

    @Getter
    @Builder
    class Mensagem{

        @Singular
        private Set<String> destinatarios;

        @NotNull
        private String assunto;

        @NotNull
        private String corpo;
    }
}

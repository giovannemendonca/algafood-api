package com.algaworks.algafood.core.email;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {

    @NotNull
    private String remetente;
    private Sandbox sandbox = new Sandbox();
    private Implementacao implementacao = Implementacao.MOCK;

    public enum Implementacao {
        SMTP, MOCK, SANDBOX
    }

    @Getter
    @Setter
    public static class Sandbox {
        private String destinatario;
    }


}

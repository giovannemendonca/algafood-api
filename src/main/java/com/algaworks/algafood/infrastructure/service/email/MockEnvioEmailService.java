package com.algaworks.algafood.infrastructure.service.email;

import com.algaworks.algafood.domain.service.EnvioEmailService;
import java.util.logging.Logger;

public class MockEnvioEmailService implements EnvioEmailService {

    Logger log = Logger.getLogger(MockEnvioEmailService.class.getName());

    @Override
    public void enviar(Mensagem mensagem) {
        log.info("[MOCK] E-mail enviado para: " + mensagem.getDestinatarios());
    }
}

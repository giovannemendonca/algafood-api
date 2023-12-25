package com.algaworks.algafood.infrastructure.service.email;

import com.algaworks.algafood.core.email.EmailProperties;
import freemarker.template.Configuration;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SandBoxEnvioEmailService extends SmtpEnvioEmailService {

    private final EmailProperties emailProperties;


    public SandBoxEnvioEmailService(JavaMailSender javaMailSender, EmailProperties emailProperties, Configuration configuration) {
        super(javaMailSender, emailProperties, configuration);
        this.emailProperties = emailProperties;
    }


    @Override
    protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {

        MimeMessage mimeMessage = super.criarMimeMessage(mensagem);
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setTo(emailProperties.getSandbox().getDestinatario());

        return mimeMessage;
    }
}

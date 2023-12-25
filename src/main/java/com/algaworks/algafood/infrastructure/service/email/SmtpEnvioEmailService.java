package com.algaworks.algafood.infrastructure.service.email;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


public class SmtpEnvioEmailService implements EnvioEmailService {

    private JavaMailSender mailSender;

    private EmailProperties emailProperties;

    private Configuration freemarkerConfig;

    public SmtpEnvioEmailService(JavaMailSender javaMailSender, EmailProperties emailProperties, Configuration configuration){
        this.mailSender= javaMailSender;
        this.emailProperties= emailProperties;
        this.freemarkerConfig = configuration;
    }

    public void enviar(Mensagem mensagem) {

        try {
            MimeMessage mimeMessage = criarMimeMessage(mensagem);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EmailException("Não foi possível enviar e-mail", e);
        }
    }

    protected String processarTemplate(Mensagem mensagem) {
        try {
            Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());

        } catch (Exception e) {
            throw new EmailException("Não foi possível montar o template do e-mail", e);
        }
    }

    protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
        String corpo = processarTemplate(mensagem);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        helper.setTo(mensagem.getDestinatarios().toArray(new String[0])); // converte Set em um array
        helper.setFrom(emailProperties.getRemetente());
        helper.setSubject(mensagem.getAssunto());
        helper.setText(corpo, true);

        return mimeMessage;
    }
}

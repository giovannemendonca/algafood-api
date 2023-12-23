package com.algaworks.algafood.core.email;

import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.infrastructure.service.email.MockEnvioEmailService;
import com.algaworks.algafood.infrastructure.service.email.SmtpEnvioEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;


@Configuration
public class EmailConfig {


    private final JavaMailSender mailSender;

    private final EmailProperties emailProperties;

    private final freemarker.template.Configuration freemarkerConfig;

    EmailConfig(EmailProperties emailProperties, JavaMailSender javaMailSender, freemarker.template.Configuration freemarkerConfig) {
        this.emailProperties = emailProperties;
        this.mailSender = javaMailSender;
        this.freemarkerConfig = freemarkerConfig;
    }

    @Bean
    public EnvioEmailService envioEmailService() {

        switch (emailProperties.getImplementacao()) {
            case MOCK:
                return new MockEnvioEmailService();
            case SMTP:
                return new SmtpEnvioEmailService(mailSender, emailProperties, freemarkerConfig);
            default:
                return null;
        }
    }


}

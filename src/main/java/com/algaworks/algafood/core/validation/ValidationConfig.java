package com.algaworks.algafood.core.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


@Configuration
public class ValidationConfig {

  // Configurando o Bean Validation para usar o MessageSource do Spring
  // para resolver mensagens do arquivo messages.properties
  // se nao configurar, o Bean Validation vai usar o ValidationMessages.properties
  // que vem junto com o Hibernate Validator

  @Bean
  public LocalValidatorFactoryBean validator(MessageSource messageSource) {
    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
    bean.setValidationMessageSource(messageSource);
    return bean;
  }

}

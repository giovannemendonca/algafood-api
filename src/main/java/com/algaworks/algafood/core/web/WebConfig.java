package com.algaworks.algafood.core.web;


import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ApiDeprecationHandler apiDeprecationHandler;

    @Autowired
    private ApiRetirementHanlder apiRetirementHanlder;

    // esse metodo addCorsMappings é necessário para que o Spring possa interceptar as requisições e adicionar o cabeçalho CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**") // todos os caminhos
                .allowedOrigins("*") // todos os origens
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // todos os métodos
    }

    // esse metodo addInterceptors faz com que o Spring possa interceptar as requisições e adicionar o cabeçalho deprecado
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiDeprecationHandler);
     // registry.addInterceptor(apiRetirementHanlder);
    }



    // esse bean é necessário para que o Spring possa interceptar as requisições e adicionar o cabeçalho ETag
    @Bean
    public Filter shallowEtagHeaderFilter(){
        return new ShallowEtagHeaderFilter();
    }
}

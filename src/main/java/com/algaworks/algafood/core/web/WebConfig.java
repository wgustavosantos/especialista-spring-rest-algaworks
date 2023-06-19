package com.algaworks.algafood.core.web;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    ApiRentirementHandler apiRentirementHandler;

    //foi retirado pois na classe CorsConfig, a configuração de lá ja surte o mesmo efeito
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") //todos os endpoints
//                .allowedMethods("*"); //todos os métodos http
//    }

    @Bean
    public Filter shallowEtagHeaderFilter(){
        return new ShallowEtagHeaderFilter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiRentirementHandler);
    }
}

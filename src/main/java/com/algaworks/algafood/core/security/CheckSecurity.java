package com.algaworks.algafood.core.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {

    @interface Cozinhas {

        @PreAuthorize("isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)//lida em tempo de exce
        @Target(ElementType.METHOD) //apenas em métodos
        @interface PodeConsultar {}

        @PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
        @Retention(RetentionPolicy.RUNTIME)//lida em tempo de exce
        @Target(ElementType.METHOD) //apenas em métodos
        @interface PodeEditar {}

    }
}

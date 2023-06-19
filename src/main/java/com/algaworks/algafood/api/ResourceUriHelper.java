package com.algaworks.algafood.api;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.http.HttpServletResponse;
import java.net.URI;

@UtilityClass
public class ResourceUriHelper {

    public static void addUriInResponseHeader(Object resourceId){

        /*Monta uma uri vinda da request. URI do recurso criado*/
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{cidadeId}")
                .buildAndExpand(resourceId).toUri();

            /*Classe Holder para expor a solicitação da Web na forma de um objeto
             RequestAttributes associado a thread*/
        final ServletRequestAttributes requestAttributes =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());

        final HttpServletResponse response = requestAttributes.getResponse();

        /*Header enviado na resposta com a uri*/
        response.addHeader(HttpHeaders.LOCATION, uri.toString());

    }
}

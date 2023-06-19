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
@ConfigurationProperties(prefix = "algafood.email")
public class EmailProperties {


    @NotNull
    private String remetente;

    private ImplEmail implementacao = ImplEmail.MOCK;
    private Sandbox sandbox = new Sandbox();

    public enum ImplEmail{
        MOCK,
        SENDGRID,
        SANDBOX;
    }

    @Getter
    @Setter
    public class Sandbox{
        private String destinatario;
    }
}

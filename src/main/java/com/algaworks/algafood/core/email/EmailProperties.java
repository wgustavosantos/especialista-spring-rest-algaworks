package com.algaworks.algafood.core.email;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.logging.Logger;

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

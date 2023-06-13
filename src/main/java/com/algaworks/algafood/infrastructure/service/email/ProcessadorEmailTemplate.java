package com.algaworks.algafood.infrastructure.service.email;

import com.algaworks.algafood.domain.service.EnvioEmailSerivce;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Component
public class ProcessadorEmailTemplate {

    @Autowired
    private Configuration freemarkerConfig;

    public String processarTemplate(EnvioEmailSerivce.Mensagem mensagem){
        try {
            final Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
        } catch (Exception e) {
            throw new EmailException("Não foi possível montar o template do e-mail.", e);
        }

    }

}

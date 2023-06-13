package com.algaworks.algafood.infrastructure.service.email;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailSerivce;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
public class SmtpEnvioEmailService implements EnvioEmailSerivce {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private ProcessadorEmailTemplate processadorEmailTemplate;

    @Override
    public void enviar(Mensagem mensagem) {
        try {
            MimeMessage mimeMessage = criarMimeMessage(mensagem);

            /* envio da mensagem construida */
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Não foi possível enviar e-mail", e);
        }
    }

    protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
        /*Objeto de email*/
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        final MimeMessageHelper helperMimeMessage = new MimeMessageHelper(mimeMessage, "UTF-8");/* encapsua mimeMessage */


        /* construindo mensagem */
        String corpo = processadorEmailTemplate.processarTemplate(mensagem);
        helperMimeMessage.setTo(mensagem.getDestinatarios().toArray(new String[0]));
        helperMimeMessage.setSubject(mensagem.getAssunto());
        helperMimeMessage.setText(corpo, true);
        helperMimeMessage.setFrom(emailProperties.getRemetente());

        return mimeMessage;
    }

}

package com.algaworks.algafood.infrastructure.service.email;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class SmtpEnvioEmailService implements EnvioEmailSerivce {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailProperties emailProperties;
    @Override
    public void enviar(Mensagem mensagem) {
        /*Objeto de email*/
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        final MimeMessageHelper helperMimeMessage = new MimeMessageHelper(mimeMessage, "UTF-8");/* encapsua mimeMessage */

        try {
            /* construindo mensagem */
            helperMimeMessage.setTo(mensagem.getDestinatarios().toArray(new String[0]));
            helperMimeMessage.setSubject(mensagem.getAssunto());
            helperMimeMessage.setText(mensagem.getCorpo(), true);
            helperMimeMessage.setFrom(emailProperties.getRementente());

            /* envio da mensagem construida */
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Não foi possível enviar e-mail", e);
        }



    }
}

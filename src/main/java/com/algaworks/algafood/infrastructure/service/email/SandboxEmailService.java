package com.algaworks.algafood.infrastructure.service.email;

import com.algaworks.algafood.core.email.EmailProperties;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SandboxEmailService extends SmtpEnvioEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private Configuration freemarkerConfig;

    @Override
    protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
        final MimeMessage mimeMessage = super.criarMimeMessage(mensagem);

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        mimeMessageHelper.setTo(emailProperties.getSandbox().getDestinatario());
        return mimeMessage;
    }
}

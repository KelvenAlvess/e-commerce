package com.example.marketPlace.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public void sendPaymentConfirmation(String recipientEmail, Long orderId, BigDecimal amount) {
        log.info("Iniciando envio de e-mail para: {}", recipientEmail);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(senderEmail);
            helper.setTo(recipientEmail);
            helper.setSubject("Pagamento Aprovado - Pedido #" + orderId);

            String htmlContent = String.format("""
                <html>
                    <body>
                        <h1>Pagamento Confirmado! 🚀</h1>
                        <p>Olá,</p>
                        <p>Recebemos o pagamento do seu pedido <strong>#%d</strong> no valor de <strong>R$ %.2f</strong>.</p>
                        <p>O vendedor já foi notificado e começará a preparar seu envio.</p>
                        <br/>
                        <p>Atenciosamente,<br/>Equipe Marketplace</p>
                    </body>
                </html>
                """, orderId, amount);

            helper.setText(htmlContent, true); // true = HTML

            mailSender.send(message);
            log.info("E-mail enviado com sucesso para {}", recipientEmail);

        } catch (MessagingException e) {
            log.error("Falha ao enviar e-mail: ", e);
        }
    }
}
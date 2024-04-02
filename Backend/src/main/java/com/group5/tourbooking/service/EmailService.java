package com.group5.tourbooking.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String verificationUUID) {
        // Crear un MimeMessage en lugar de SimpleMailMessage
        MimeMessage message = mailSender.createMimeMessage();

        try {
            // Habilitar el soporte para HTML y UTF-8
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("tourbookinglatam@hotmail.com");
            helper.setTo(to);
            helper.setSubject("Verificación de cuenta");

            // Suponiendo que la aplicación se ejecuta en "http://localhost:8080"
            String verificationURL = "http://localhost:8080/verification/verifyAccount?uuid=" + verificationUUID;

            // Ahora se puede utilizar HTML en el contenido del correo
            String htmlContent = "<p>Por favor haz clic en el siguiente enlace para verificar tu cuenta:</p>"
                    + "<a href='" + verificationURL + "'>" + verificationURL + "</a>";

            helper.setText(htmlContent, true); // true indica que el contenido es HTML

            mailSender.send(message);
        } catch (MessagingException e) {
            // Aquí manejarías cualquier excepción relacionada con la creación o envío del mensaje.
            e.printStackTrace();
        }
    }
}
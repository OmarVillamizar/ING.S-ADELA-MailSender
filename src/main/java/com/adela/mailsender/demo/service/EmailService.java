package com.adela.mailsender.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Envía un correo de bienvenida al estudiante asignado a un grupo.
     *
     * @param to              Dirección de correo del estudiante
     * @param nombre          Nombre del estudiante
     * @param grupo           Nombre del grupo
     */
    @Async
    public void sendEmail(String to, String nombre, String grupo) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("¡Bienvenido a tu grupo, " + nombre + "!");
            message.setText("Hola " + nombre + ",\n\n"
                    + "Has sido asignado al grupo: " + grupo + ".\n\n"
                    + "Saludos,\n"
                    + "Equipo ADELA 🧠");

            mailSender.send(message);
            log.info("Correo de bienvenida enviado exitosamente a {}", to);

        } catch (Exception e) {
            log.error("Error al enviar correo a {}: {}", to, e.getMessage());
        }
    }
}

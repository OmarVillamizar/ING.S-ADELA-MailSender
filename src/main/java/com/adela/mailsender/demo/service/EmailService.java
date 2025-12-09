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
    
    @Autowired 
    private JavaMailSender mailSender;
    
    private static final String FROM = "adelabot.noresponder@gmail.com";
    
    /**
     * Envía un correo de bienvenida al estudiante con su grupo asignado 
     * y enlace al aplicativo de detección de estilos de aprendizaje.
     *
     * @param to      Correo del estudiante
     * @param nombre  Nombre del estudiante
     * @param grupo   Grupo asignado
     */
    @Async
    public void sendEmail(String to, String nombre, String grupo) {
        String webUrl = "https://ings-adela-frontend.vercel.app/";
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(FROM);
            message.setTo(to);
            message.setSubject("🎓 ¡Bienvenido/a a ADELA - Tu grupo ha sido asignado!");
            
            StringBuilder emailBody = new StringBuilder();
            emailBody.append("╔══════════════════════════════════════════════╗\n");
            emailBody.append("║   ADELA - Detección de Estilos de Aprendizaje   ║\n");
            emailBody.append("╚══════════════════════════════════════════════╝\n\n");
            
            emailBody.append("Hola ").append(nombre).append(",\n\n");
            
            emailBody.append("¡Bienvenido/a al sistema ADELA! 🧠✨\n\n");
            
            emailBody.append("Nos complace informarte que has sido asignado/a al siguiente grupo:\n");
            emailBody.append("📚 Grupo: ").append(grupo).append("\n\n");
            
            emailBody.append("─────────────────────────────────────────────\n\n");
            
            emailBody.append("🌐 Accede a nuestra plataforma:\n");
            emailBody.append("Por favor, ingresa desde el siguiente enlace:\n");
            emailBody.append("🔗 ").append(webUrl).append("\n\n");
            
            emailBody.append("─────────────────────────────────────────────\n\n");
            
            emailBody.append("💡 ¿Qué es ADELA?\n");
            emailBody.append("ADELA es un sistema inteligente diseñado para identificar\n");
            emailBody.append("tu estilo de aprendizaje y personalizar tu experiencia\n");
            emailBody.append("educativa según tus necesidades individuales.\n\n");
            
            emailBody.append("📋 Próximos pasos:\n");
            emailBody.append("   1. Accede a la plataforma usando el enlace proporcionado\n");
            emailBody.append("   2. Completa tu perfil de usuario\n");
            emailBody.append("   3. Realiza la evaluación de estilos de aprendizaje\n");
            emailBody.append("   4. ¡Descubre cómo aprendes mejor!\n\n");
            
            emailBody.append("─────────────────────────────────────────────\n\n");
            
            emailBody.append("Si tienes alguna pregunta o necesitas asistencia,\n");
            emailBody.append("no dudes en contactarnos.\n\n");
            
            emailBody.append("¡Te deseamos mucho éxito en tu proceso de aprendizaje! 🚀\n\n");
            
            emailBody.append("Atentamente,\n");
            emailBody.append("Equipo ADELA 🧠\n");
            emailBody.append("Sistema de Detección de Estilos de Aprendizaje\n\n");
            
            emailBody.append("───────────────────────────────────────────────\n");
            emailBody.append("Este es un correo automático, por favor no responder.\n");
            
            message.setText(emailBody.toString());
            
            mailSender.send(message);
            log.info("📬 Correo enviado exitosamente a {} (Grupo: {})", to, grupo);
            
        } catch (Exception e) {
            log.error("❌ Error al enviar correo a {}: {}", to, e.getMessage(), e);
        }
    }
}
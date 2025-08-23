package com.codex.ecomerce.services;

import jakarta.mail.internet.MimeMessage;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendVerificationOtpEmail(String userEmail, String otp, String subject, String text){
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setSubject(subject);
            helper.setText(text + ": " + otp, true); // mark true for HTML support
            helper.setTo(userEmail);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new MailSendException("Failed to send email: " + e.getMessage());
        }
    }
}


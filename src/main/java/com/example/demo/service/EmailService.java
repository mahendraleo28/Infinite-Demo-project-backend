package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }
    public void sendEmailWithAttachment(String to, String subject, String text, byte[] attachmentData, String attachmentFilename) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, false); // Set the text as HTML or plain text

            // Attach the Excel file
            ByteArrayResource attachment = new ByteArrayResource(attachmentData);
            helper.addAttachment(attachmentFilename, attachment);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            // Handle the exception, e.g., log an error
            e.printStackTrace();
        }
    }
}

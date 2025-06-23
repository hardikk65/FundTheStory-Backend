package com.app.FundTheStory.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String email;


    public void sendEmail(String toEmail, String name, String campaign, double amount){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setFrom(email);
        message.setSubject("Donation Successful - " + campaign);
        message.setText("Hi " + name + " Thank You for being part of  " + campaign + " campaign and Donating: " + amount + ". We appreciate your help. ");

        mailSender.send(message);
    }
}

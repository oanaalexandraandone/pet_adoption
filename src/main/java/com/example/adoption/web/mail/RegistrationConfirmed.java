package com.example.adoption.web.mail;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class RegistrationConfirmed {

MailConfig mailConfig;

    public RegistrationConfirmed(MailConfig mailConfig) {
        this.mailConfig = mailConfig;
    }

    public void send(String to)  {
        String message="Your account was created successfully.";
        String subject="Registration confirmed";

        Properties properties= new Properties();
        properties.put("mail.smtp.host", mailConfig.getHost());
        properties.put("mail.smtp.socketFactory.port", mailConfig.getPort());
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", mailConfig.getPort());
        Session session=Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailConfig.getUsername(), mailConfig.getPassword());
                    }
                });

        try{
            MimeMessage mail= new MimeMessage(session);
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mail.setSubject(subject);
            mail.setText(message);
            Transport.send(mail);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
}

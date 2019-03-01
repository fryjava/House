package com.xmcc.House.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailUtils {

    @Autowired
   private  JavaMailSender javaMailSender ;

    @Value("${email.from}")
    private String from;
    public void sendMail(String email,String url,String content){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(content);
        simpleMailMessage.setText(url);
        javaMailSender.send(simpleMailMessage);
    }




}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.disney.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rodrigo Caro
 */

@Service
public class MailServicio {
    @Autowired
    public JavaMailSenderImpl mailSender;
    
    
    public void enviarMail(String body, String title, String mail){
     
        SimpleMailMessage msj = new SimpleMailMessage();
        msj.setTo(mail);
        msj.setFrom("loscanuteros06@hotmail.com");
        msj.setSubject(title);
        msj.setText(body);
        
        mailSender.send(msj);
    }
}

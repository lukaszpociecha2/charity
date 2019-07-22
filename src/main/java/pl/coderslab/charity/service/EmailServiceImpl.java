package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void send(String from, String to, String title, String body) {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(title);
            message.setText(body);
            emailSender.send(message);
    }
}

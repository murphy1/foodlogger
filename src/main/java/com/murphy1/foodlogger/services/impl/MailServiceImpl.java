package com.murphy1.foodlogger.services.impl;

import com.murphy1.foodlogger.model.PasswordResetToken;
import com.murphy1.foodlogger.repositories.UserRepository;
import com.murphy1.foodlogger.services.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MailServiceImpl implements MailService {

    private UserRepository userRepository;
    private JavaMailSender javaMailSender;

    public MailServiceImpl(UserRepository userRepository, JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendForgotPasswordMail(String email, PasswordResetToken token) {

        // This will be triggered when the user forgot password and uses the reset functionality

        var mailMessage = new SimpleMailMessage();

        mailMessage.setTo(userRepository.findUserByEmail(email).block().getEmail());
        mailMessage.setSubject("Reset Password for FoodLogger");
        mailMessage.setText("Hello, "+
                "\n\nPlease go to the following link to reset your password:"+"\n\n"+
                "http://localhost:8080/process/"+token.getToken()+
                "\n\n"+
                "Warning, this token will expire in 24 hours!"
        );
        log.debug("Sending password reset message!");
        javaMailSender.send(mailMessage);
    }
}

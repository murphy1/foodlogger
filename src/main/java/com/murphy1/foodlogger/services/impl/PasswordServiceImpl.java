package com.murphy1.foodlogger.services.impl;

import com.murphy1.foodlogger.exceptions.BadRequestException;
import com.murphy1.foodlogger.model.PasswordResetToken;
import com.murphy1.foodlogger.model.User;
import com.murphy1.foodlogger.repositories.PasswordTokenRepository;
import com.murphy1.foodlogger.repositories.UserRepository;
import com.murphy1.foodlogger.services.MailService;
import com.murphy1.foodlogger.services.PasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Slf4j
@Service
public class PasswordServiceImpl implements PasswordService {

    private PasswordTokenRepository passwordTokenRepository;
    private UserRepository userRepository;
    private MailService mailService;

    public PasswordServiceImpl(PasswordTokenRepository passwordTokenRepository, UserRepository userRepository, MailService mailService) {
        this.passwordTokenRepository = passwordTokenRepository;
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    @Override
    public PasswordResetToken generateToken(String email) {
        Mono<User> user = userRepository.findUserByEmail(email);

        if (user.block() == null){
            throw new BadRequestException("User does not exist with email: "+email);
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setEmail(email);

        passwordTokenRepository.save(token).block();
        mailService.sendForgotPasswordMail(email, token);

        return token;
    }

    @Override
    public User processToken(String token) {

        // Method will process a token. This is hit when the user clicks the password reset link in their email.

        // Will check if token exists and is not older than 24 hours. Will then find and return associated user.

        Mono<PasswordResetToken> tokenToCheck = passwordTokenRepository.findByToken(token);

        if (tokenToCheck.block() == null){
            log.error("Token not found in database!");
            throw new BadRequestException("Token is not valid!");
        }

        if (tokenToCheck.block().getDateCreated().isBefore(LocalDate.now())){
            log.error("Token is not valid. Older than 24 hours! Deleting Token");
            passwordTokenRepository.delete(tokenToCheck.block()).block();
            throw new BadRequestException("Token is not valid!");
        }

        User user = userRepository.findUserByEmail(tokenToCheck.block().getEmail()).block();

        passwordTokenRepository.delete(tokenToCheck.block()).block();

        return user;
    }
}

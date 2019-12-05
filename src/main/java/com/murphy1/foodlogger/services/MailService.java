package com.murphy1.foodlogger.services;

import com.murphy1.foodlogger.model.PasswordResetToken;

public interface MailService {

    void sendForgotPasswordMail(String email, PasswordResetToken token);

}

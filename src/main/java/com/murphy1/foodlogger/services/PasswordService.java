package com.murphy1.foodlogger.services;

import com.murphy1.foodlogger.model.PasswordResetToken;
import com.murphy1.foodlogger.model.User;

public interface PasswordService {

    PasswordResetToken generateToken(String email);
    User processToken(String token);

}

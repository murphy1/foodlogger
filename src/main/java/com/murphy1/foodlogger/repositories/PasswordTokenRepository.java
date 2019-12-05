package com.murphy1.foodlogger.repositories;

import com.murphy1.foodlogger.model.PasswordResetToken;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PasswordTokenRepository extends ReactiveCrudRepository<PasswordResetToken, String> {

    Mono<PasswordResetToken> findByToken(String token);

}

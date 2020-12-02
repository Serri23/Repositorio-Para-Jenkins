package com.practica.gateway.repositories;

import com.practica.gateway.domains.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User,String> {

    Mono<UserDetails> findByUsername(String username);
}

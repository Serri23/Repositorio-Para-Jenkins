package com.practica.gateway.controllers;

import com.practica.gateway.domains.User;
import com.practica.gateway.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/api/user/")
    public Mono<User> creaUser(@RequestBody User user){
        return userService.creaUser(user);
    }

}

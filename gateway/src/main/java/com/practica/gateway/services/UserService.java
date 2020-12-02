package com.practica.gateway.services;

import com.practica.gateway.domains.User;
import com.practica.gateway.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Mono<User> creaUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        SimpleGrantedAuthority rol = new SimpleGrantedAuthority("ROLE_ADMIN");
        roles.add(rol);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    /*public User logueame(String username, String password) {
        Mono<UserDetails> userInDb = userRepository.findByUsername(username);
        UserDetails user = userInDb.block();

        if(user.getUsername() != null){
            System.out.println("Username en uso");
            return null;
        }else{
            String passwordE = passwordEncoder.encode(password);
            String domain = "Usuarios";
            User myUser = new User(username,passwordE,domain);
            Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
            GrantedAuthority rol = new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "ROL_USUARIO";
                }
            };

            roles.add(rol);
            myUser.setRoles(roles);
            return myUser;
        }


    }*/
}

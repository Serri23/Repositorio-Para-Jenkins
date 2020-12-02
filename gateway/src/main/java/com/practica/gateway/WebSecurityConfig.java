package com.practica.gateway;

import com.practica.gateway.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class WebSecurityConfig {
    @Autowired
    private UserRepository userRepository;
    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return (username) -> userRepository.findByUsername(username);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http){
        //ServerHttpSecurity.AuthorizeExchangeSpec authorizeExchangeSpec = http.authorizeExchange().pathMatchers("/actuator/**").authenticated();
        ServerHttpSecurity.AuthorizeExchangeSpec authorizeExchangeSpec = http.authorizeExchange().pathMatchers("/actuator/**").hasRole("ADMIN");
        authorizeExchangeSpec.pathMatchers("/api/user/**").permitAll();
        //Si tiene el usuario tiene rol->pagos,podra acceder si no,no
        /*authorizeExchangeSpec.pathMatchers("/api/user/pago/**").hasRole("PAGOS");
        authorizeExchangeSpec.pathMatchers("/api/user/factura/**").hasRole("FACTURACION");
        authorizeExchangeSpec.pathMatchers("/api/user/cliente/**").hasRole("CLIENTES");
        authorizeExchangeSpec.pathMatchers("/api/user/visita/**").hasRole("VISITAS");*/
        authorizeExchangeSpec.and().csrf().disable().httpBasic().and().formLogin().loginPage("/login");
        return http.build();


    }


}

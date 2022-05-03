package com.example.basicaut.seguretat;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderConfig {
    @Bean
    public PasswordEncoder xifrat(){
        return new BCryptPasswordEncoder();
    }
}

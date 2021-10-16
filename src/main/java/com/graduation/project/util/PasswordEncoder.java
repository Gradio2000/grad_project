package com.graduation.project.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PasswordEncoder extends org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder {

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new PasswordEncoder();
    }
}

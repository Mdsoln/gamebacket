package com.gamebacket.vercel.app.config;

import com.gamebacket.vercel.app.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig{
    private final UserRepo userRepo;

    @Bean
    public UserDetailsService userDetailsService(){
        return userRepo::findByUserEmail;
    }
}

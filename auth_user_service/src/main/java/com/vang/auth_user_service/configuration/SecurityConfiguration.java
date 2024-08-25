package com.vang.auth_user_service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(CsrfConfigurer::disable);
        http.authorizeHttpRequests(auth -> {

            auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/auth/user/")).permitAll()
                    .anyRequest().authenticated();
        });
        return http.build();
    }

}
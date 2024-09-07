package com.vang.oauth_service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers(AntPathRequestMatcher.antMatcher("/")).permitAll()
                            .anyRequest().authenticated();
                })
                .oauth2Login(oauth2 -> {
//                    oauth2.loginPage("/api/v1/oauth/google");
//                    oauth2.loginPage("/api/v1/oauth/google");
                    oauth2.defaultSuccessUrl("/google-success");
                })
                .formLogin(Customizer.withDefaults()).build();
    }
}
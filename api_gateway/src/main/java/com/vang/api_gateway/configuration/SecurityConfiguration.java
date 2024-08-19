package com.vang.api_gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfiguration {

    @Bean
    public CorsConfigurationSource corsConfiguration() {

        UrlBasedCorsConfigurationSource cors = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173"));
        cors.registerCorsConfiguration("/**", corsConfiguration);
        return cors;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(CsrfConfigurer::disable);
        http.cors(cors -> {
            cors.configurationSource(corsConfiguration());
        });
        http.authorizeHttpRequests(auth -> {
           auth.requestMatchers(AccessRole.nonAccessRoles()).permitAll()
                   .anyRequest().authenticated();
        });
        return http.build();
    }
}
package com.vang.api_gateway.configuration;

import com.vang.api_gateway.common.Common;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Service
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Autowired
    public JwtFilter(final JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(Common.AUTHORIZATION);
        String token = null;

        if (header != null && header.startsWith(Common.BEARER)) {

            token = header.substring(7);
        }
        if (token != null && jwtService.isValidateToken(token) && SecurityContextHolder.getContext().getAuthentication() == null) {

            String role = jwtService.extractRole(token);
            String username = jwtService.extractUsername(token);
            UserDetails userDetails = new User(username, Common.EMPTY_STRING, List.of(new SimpleGrantedAuthority(role)));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
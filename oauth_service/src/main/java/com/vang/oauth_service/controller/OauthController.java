package com.vang.oauth_service.controller;

import com.vang.oauth_service.service.Oauth2Service;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class OauthController {

    private final Oauth2Service oauth2Service;

    @Autowired
    public OauthController(Oauth2Service oauth2Service) {
        this.oauth2Service = oauth2Service;
    }

    @GetMapping("/google-success")
    public ModelAndView getAuth(HttpServletResponse response, Authentication authentication) {

        return oauth2Service.authenticate(response, authentication);
    }
}
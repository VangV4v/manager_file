package com.vang.oauth_service.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
//@RequestMapping("/api/v1/oauth/google/")
public class OauthController {

    @GetMapping("/")
    public String home() {
        return  "Hello";
    }

    @GetMapping("/secured")
    public String secured() {

        return "Secured";
    }

    @GetMapping("/google-success")
    public ModelAndView getAuth(HttpServletResponse response, Authentication authentication) {

        Authentication auth = authentication;
        return new ModelAndView("redirect:http://localhost:5173/home");
    }
}
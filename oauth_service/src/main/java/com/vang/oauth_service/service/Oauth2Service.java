package com.vang.oauth_service.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.ModelAndView;

public interface Oauth2Service {

    ModelAndView authenticate(HttpServletResponse response, Authentication authentication);
}
package com.vang.oauth_service.service.impl;

import com.vang.oauth_service.common.OauthCommon;
import com.vang.oauth_service.data.UserRepository;
import com.vang.oauth_service.data.Users;
import com.vang.oauth_service.model.UserModel;
import com.vang.oauth_service.service.Oauth2Service;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class Oauth2ServiceImpl implements Oauth2Service {

    private final UserRepository userRepository;

    @Autowired
    public Oauth2ServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ModelAndView authenticate(HttpServletResponse response, Authentication authentication) {

        DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
        long checkExistData = userRepository.countByUsernameAndEmail(principal.getAttribute("sub"), principal.getAttribute("email"));
        if(checkExistData == 0) {

            UserModel userModel = new UserModel();
            Users users = new Users();
            userModel.setUserId(OauthCommon.generateStringId());
            userModel.setFirstName(principal.getAttribute("given_name"));
            userModel.setLastName(principal.getAttribute("family_name"));
            userModel.setEmail(principal.getAttribute("email"));
            userModel.setPassword(OauthCommon.generateStringId());
            userModel.setUsername(principal.getAttribute("sub"));
            userModel.setStatus(1);
            userModel.setType(1);
            userModel.setCreatedDate(OauthCommon.getCurrentDate());
            BeanUtils.copyProperties(userModel, users);
            userRepository.save(users);
        }

        return new ModelAndView(OauthCommon.REDIRECT_HOME);
    }
}
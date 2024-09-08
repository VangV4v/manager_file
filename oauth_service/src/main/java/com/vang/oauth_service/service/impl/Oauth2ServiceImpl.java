package com.vang.oauth_service.service.impl;

import com.vang.oauth_service.common.OauthCommon;
import com.vang.oauth_service.data.UserRepository;
import com.vang.oauth_service.data.Users;
import com.vang.oauth_service.grpc.grpc.AuthUserClientImpl;
import com.vang.oauth_service.model.UserModel;
import com.vang.oauth_service.service.Oauth2Service;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class Oauth2ServiceImpl implements Oauth2Service {

    private final UserRepository userRepository;
    private final AuthUserClientImpl authUserClient;

    @Autowired
    public Oauth2ServiceImpl(UserRepository userRepository, AuthUserClientImpl authUserClient) {
        this.userRepository = userRepository;
        this.authUserClient = authUserClient;
    }

    @Override
    public ModelAndView authenticate(HttpServletResponse response, Authentication authentication) {

        ModelAndView responseModel = new ModelAndView(OauthCommon.REDIRECT_HOME);
        DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
        long checkExistData = userRepository.countByUsernameAndEmail(principal.getAttribute("sub"), principal.getAttribute("email"));
        if(checkExistData == 0) {

            UserModel userModel = new UserModel();
            Users users = new Users();
            userModel.setUserId(OauthCommon.generateStringId());
            userModel.setFirstName(principal.getAttribute(OauthCommon.GIVEN_NAME));
            userModel.setLastName(principal.getAttribute(OauthCommon.FAMILY_NAME));
            userModel.setEmail(principal.getAttribute(OauthCommon.EMAIL));
            userModel.setPassword(OauthCommon.generateStringId());
            userModel.setUsername(principal.getAttribute(OauthCommon.SUB));
            userModel.setStatus(1);
            userModel.setType(1);
            userModel.setCreatedDate(OauthCommon.getCurrentDate());
            BeanUtils.copyProperties(userModel, users);
            userRepository.save(users);
        }
        String jwt = authUserClient.authenticateOauth(principal.getAttribute(OauthCommon.SUB));
        responseModel.addObject("str", "hsgyanaygyYadbggasdbhhjjjhasd");
        responseModel.addObject("jwt", jwt);
        responseModel.addObject("expiration", System.currentTimeMillis()+ (1000 * 60 * 30)+"");
        return responseModel;
    }
}
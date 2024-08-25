package com.vang.auth_admin_service.service.impl;

import com.vang.auth_admin_service.common.AuthAdminCommon;
import com.vang.auth_admin_service.jwt.JwtService;
import com.vang.auth_admin_service.model.AuthRequestModel;
import com.vang.auth_admin_service.model.AuthResponseModel;
import com.vang.auth_admin_service.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public AuthenticateServiceImpl(JwtService jwtService, AuthenticationManager authenticationManager, RedisTemplate<String, Object> redisTemplate) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Future<ResponseEntity<AuthResponseModel>> authenticate(AuthRequestModel requestModel) {

        AuthResponseModel responseModel = new AuthResponseModel();
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestModel.getUsername(), requestModel.getPassword()));
            if (authentication.isAuthenticated()) {

                //Save temp data into Redis
                redisTemplate.opsForValue().set(AuthAdminCommon.USERNAME_ADMIN_KEY, requestModel.getUsername());
                redisTemplate.opsForValue().set(AuthAdminCommon.USERNAME_EXPIRATION_ADMIN_KEY, System.currentTimeMillis()+(1000 * 60 * 30));
                //end save
                responseModel.setSuccess(Boolean.TRUE);
                responseModel.setRole(AuthAdminCommon.ROLE_ADMIN);
                responseModel.setExpiration(System.currentTimeMillis() + (1000 * 60 * 30));
                responseModel.setJwt(jwtService.generateToken(requestModel.getUsername()));
                ResponseEntity<AuthResponseModel> res = new ResponseEntity<>(responseModel, HttpStatus.OK);
                return CompletableFuture.completedFuture(res);

            }
        } catch (BadCredentialsException e) {

            responseModel.setSuccess(Boolean.FALSE);
            responseModel.setMessage(AuthAdminCommon.MES_AUTH_FAIL);
            ResponseEntity<AuthResponseModel> res = new ResponseEntity<>(responseModel, HttpStatus.UNAUTHORIZED);
            return CompletableFuture.completedFuture(res);
        }
        return null;
    }
}
package com.vang.auth_user_service.jwt;

import com.vang.auth_user_service.common.AuthUserCommon;
import com.vang.auth_user_service.grpc.grpc.UserClientGrpc;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserClientGrpc userClient;

    @Autowired
    public MyUserDetailsService(UserClientGrpc userClient) {
        this.userClient = userClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String passwordResult = userClient.authenticateUser(username);
        List<GrantedAuthority> listGrant = List.of(new SimpleGrantedAuthority(AuthUserCommon.ROLE_VALUE));
        if(StringUtils.isEmpty(passwordResult)) {

            throw new UsernameNotFoundException("Username is not found");
        }
        return new User(username, passwordResult, listGrant);
    }

}
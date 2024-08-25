package com.vang.auth_admin_service.jwt;

import com.vang.auth_admin_service.common.AuthAdminCommon;
import com.vang.auth_admin_service.grpc.grpc.AdminClientGrpc;
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

    private final AdminClientGrpc adminClientGrpc;

    @Autowired
    public MyUserDetailsService(AdminClientGrpc adminClientGrpc) {
        this.adminClientGrpc = adminClientGrpc;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String password = adminClientGrpc.authenticate(username);
        List<GrantedAuthority> listGrant = List.of(new SimpleGrantedAuthority(AuthAdminCommon.ROLE_ADMIN));
        if(StringUtils.isEmpty(password)) {

            throw new UsernameNotFoundException("Username not found");
        }
        return new User(username, password, listGrant);
    }

}
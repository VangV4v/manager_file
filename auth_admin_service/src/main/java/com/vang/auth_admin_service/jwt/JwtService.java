package com.vang.auth_admin_service.jwt;

import com.vang.auth_admin_service.common.AuthAdminCommon;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    public String generateToken(String username) {

        Map<String, String> claims = new HashMap<String, String>();
        claims.put(AuthAdminCommon.ROLE_ADMIN_KEY, AuthAdminCommon.ROLE_ADMIN);
        return createToken(username, claims);
    }

    private String createToken(String username, Map<String, String> claims) {

        return Jwts
                .builder()
                .claims(claims)
                .subject(username)
                .signWith(getKey())
                .expiration(new Date(System.currentTimeMillis() + (1000*60*30)))
                .compact();
    }

    private Key getKey() {

        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(AuthAdminCommon.SECRET));
    }
}
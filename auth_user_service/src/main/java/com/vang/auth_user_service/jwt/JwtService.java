package com.vang.auth_user_service.jwt;

import com.vang.auth_user_service.common.AuthUserCommon;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    public String generateToken(String username) {

        Map<String, String> claims = new HashMap<String, String>();
        claims.put(AuthUserCommon.ROLE_KEY, AuthUserCommon.ROLE_VALUE);
        return createToken(username, claims);
    }

    private String createToken(String username, Map<String, String> claims) {

        return Jwts
                .builder()
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(AuthUserCommon.SECRET)))
                .subject(username)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 30)))
                .compact();
    }

}
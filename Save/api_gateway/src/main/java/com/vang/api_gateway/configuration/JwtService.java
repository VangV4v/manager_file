package com.vang.api_gateway.configuration;

import com.vang.api_gateway.common.Common;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {


    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    public boolean isValidateToken(String token) {

        return new Date().before(extractClaim(token, Claims::getExpiration));
    }

    public String extractRole(String token) {

        Claims claims = getClaims(token);
        return claims.get(Common.ROLE_KEY, String.class);
    }

    private <R> R extractClaim(String token, Function<Claims, R> claimsExtractor) {

        Claims claims = getClaims(token);
        return claimsExtractor.apply(claims);
    }

    private Claims getClaims(String token) {

        return Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(Common.SECRET)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
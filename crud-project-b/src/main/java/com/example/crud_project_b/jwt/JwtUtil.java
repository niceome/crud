package com.example.crud_project_b.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String secret = "zjfG+Z8tWIUsCO/msd4JhurYiyZ093lgHJ+MMtlAIjh2wxk6SNNOOBPFyg4QsjyoJgAqraOD8Z/BrwKxc2KqMQ==";

    private final long accessTokenTime = 1000 * 60 * 30;    // 30분
    private final long refreshTokenTime = 1000 * 60 * 60 * 24 * 14;

    public String createAccessToken(String username) {
        return createToken(username, accessTokenTime);
    }

    public String createRefreshToken(String username) {
        return createToken(username, refreshTokenTime);
    }

    public String createToken(String username, long time) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

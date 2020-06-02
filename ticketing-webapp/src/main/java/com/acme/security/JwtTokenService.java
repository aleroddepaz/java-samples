package com.acme.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenService {

    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration:864_000_000}") // Default: 10 days
    private long expiration;

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        //claims.put("userId", user.getId() + "");
        //claims.put("role", user.getRole());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public User parseToken(String token) {
        User user = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            String username = body.getSubject();
            //u.setId(Long.parseLong((String) body.get("userId")));
            //u.setRole((String) body.get("role"));
        } catch (JwtException e) {
        }
        return user;
    }
}

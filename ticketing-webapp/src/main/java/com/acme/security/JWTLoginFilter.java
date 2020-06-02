package com.acme.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";
    
    @Autowired
    JwtTokenService tokenService;
    
    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {
        String header = req.getHeader(HEADER);
        if (header == null || !header.startsWith(PREFIX)) {
            throw new AuthenticationCredentialsNotFoundException("No JWT token found in request headers");
        }
        String token = header.replace(PREFIX, "");
        tokenService.
        //new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), Collections.emptyList())
        return getAuthenticationManager().authenticate(authentication)
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth)
            throws IOException, ServletException {
        TokenAuthenticationService.generateToken(res, auth.getName());
    }

}
package com.pizzaDeliveryApp.pizzaApi.service.IMPL;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.filter.GenericFilterBean;

import com.pizzaDeliveryApp.pizzaApi.constant.Constants;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;



public class JwtFilter extends GenericFilterBean {
    static final Key SIGNING_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String authHeader = request.getHeader(Constants.Values.AUTHORIZATION);
        if(request.getMethod().equals(Constants.Values.OPTION)){
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request,response);
        }
        else if(authHeader == null || !authHeader.startsWith(Constants.Values.BEARER))
        {
            throw new ServletException(Constants.Errors.INVALID_TOKEN);
        }
        String token = authHeader.substring(7);

        Claims claims = Jwts.parserBuilder().setSigningKey(SigningKey.SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        request.setAttribute("claims",claims);

        filterChain.doFilter(request,response);
    }

}

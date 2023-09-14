package com.pizzaDeliveryApp.pizzaApi.service.IMPL;

import com.pizzaDeliveryApp.pizzaApi.entity.User;
import com.pizzaDeliveryApp.pizzaApi.service.JwtTokenGenerator;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenGenerator implements JwtTokenGenerator {

    @Override
    public String generate_Token(User user) {
        Map<String, Object> claims = new HashMap<>();
        user.setPassword("");
        claims.put("user", user); //User in token

        String jwtToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SigningKey.SIGNING_KEY)
                .compact();

        return jwtToken;
    }
}

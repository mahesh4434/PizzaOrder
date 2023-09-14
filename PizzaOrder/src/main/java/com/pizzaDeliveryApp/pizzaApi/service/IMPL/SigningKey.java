package com.pizzaDeliveryApp.pizzaApi.service.IMPL;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class SigningKey {
    static final Key SIGNING_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

}


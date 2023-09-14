package com.pizzaDeliveryApp.pizzaApi.service;

import com.pizzaDeliveryApp.pizzaApi.entity.User;

public interface JwtTokenGenerator {
    public String generate_Token(User user);


}
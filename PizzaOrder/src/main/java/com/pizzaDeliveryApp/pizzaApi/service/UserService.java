package com.pizzaDeliveryApp.pizzaApi.service;

import com.pizzaDeliveryApp.pizzaApi.dto.LoginDto;
import com.pizzaDeliveryApp.pizzaApi.dto.UserDto;
import com.pizzaDeliveryApp.pizzaApi.entity.Item;
import com.pizzaDeliveryApp.pizzaApi.exception.CartException;
import com.pizzaDeliveryApp.pizzaApi.response.LoginResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;


@Configuration
public interface UserService {

    //TODO method name should be camel case

    ResponseEntity<String> addUser(UserDto userDTO);


    boolean checkUserExists(String username);

    LoginResponse loginUser(LoginDto loginDTO);

    public Item viewOrder(Integer orderId)throws CartException;


}

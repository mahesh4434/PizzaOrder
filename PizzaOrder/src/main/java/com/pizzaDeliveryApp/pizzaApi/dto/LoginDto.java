package com.pizzaDeliveryApp.pizzaApi.dto;

import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginDto {
    private String emailId;
    private String password;

    public LoginDto() {
    }

    public LoginDto(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;

    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "username='" + emailId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

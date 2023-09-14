package com.pizzaDeliveryApp.pizzaApi.dto;

public class OrderDto {
    private String name;

    public OrderDto() {

    }

    public OrderDto(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "CustomDTO{" +
                ", name='" + name + '\'' +
                '}';
    }
}

package com.pizzaDeliveryApp.pizzaApi.entity;

import javax.persistence.*;
@Entity
@Table(name = "Sizes")
public class Size {
        @Id
        @Column(name = "SizeName", length = 255)
        private String name;

    public Size(String name) {
        this.name = name;
    }

    public Size() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.pizzaDeliveryApp.pizzaApi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Crusts")
public class Crust {
        @Id
        @Column(name = "CrustName", length = 255)
        private String name;

    public Crust(String name) {
        this.name = name;
    }

    public Crust() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

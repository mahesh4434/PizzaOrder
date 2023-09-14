package com.pizzaDeliveryApp.pizzaApi.entity;

import javax.persistence.*;

@Entity
@Table(name = "Toppings")
public class Topping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ToppingName", length = 255)
    private String name;

    public Topping(String name) {
        this.name = name;
    }

    public Topping() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

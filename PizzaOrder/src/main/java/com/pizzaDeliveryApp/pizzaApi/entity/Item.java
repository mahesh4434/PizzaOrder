package com.pizzaDeliveryApp.pizzaApi.entity;

import javax.persistence.*;

import com.pizzaDeliveryApp.pizzaApi.repository.ViewMenuRepository;

import lombok.ToString;


import java.util.Optional;


@ToString
@Table(name="Items1", uniqueConstraints = {
        @UniqueConstraint(columnNames = "OrderId")  })
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OrderId", length = 255)
    private Integer orderId=0;

    private Integer quantity;

    private String size;

    private String crust;

    private String topping;

    private String orderStatus="InProcess";

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PizzaId", referencedColumnName = "PizzaId")
    private ViewMenu pizzaId;

    public Item(Integer orderId) {
    }

    public Integer getOrderId() {
        this.orderId=orderId;
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public Item(Integer quantity, String pid, ViewMenuRepository viewMenuRepository) {
        this.quantity = quantity;
        Optional<ViewMenu> optionalViewMenu = viewMenuRepository.findById(pid);
        if (optionalViewMenu.isPresent()  ) {
            this.pizzaId = optionalViewMenu.get();

        } else {
            this.pizzaId = new ViewMenu(pid);

        }

    }

    public ViewMenu getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(ViewMenu pizzaId) {
        this.pizzaId = pizzaId;
    }


    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Item() {
    }

    @Override
    public String toString() {
        return "Item{" +
                "orderId=" + orderId +
                ", quantity=" + quantity +
                ", size='" + size + '\'' +
                ", crust='" + crust + '\'' +
                ", topping='" + topping + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", pizzaId=" + pizzaId +
                '}';
    }
}

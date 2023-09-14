package com.pizzaDeliveryApp.pizzaApi.entity;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Menu", uniqueConstraints = {
        @UniqueConstraint(columnNames = "PizzaId")
})
public class ViewMenu {

    @Id
    @Column(name = "PizzaId", length = 255)
    private String pid;

    @Column(name = "PizzaName", length = 255)
    private String name;


    @Column(name = "Price", length = 255)
    private int price;

    @ManyToMany
    @JoinTable(
            name = "Menu_Toppings",
            joinColumns = @JoinColumn(name = "menu_pid"),
            inverseJoinColumns = @JoinColumn(name = "topping_id")
    )
    private List<Topping> toppings;

    @ManyToMany
    @JoinTable(
            name = "Menu_Sizes",
            joinColumns = @JoinColumn(name = "menu_pid"),
            inverseJoinColumns = @JoinColumn(name = "size_name")
    )
    private List<Size> sizes;

    @ManyToMany
    @JoinTable(
            name = "Menu_Crusts",
            joinColumns = @JoinColumn(name = "menu_pid"),
            inverseJoinColumns = @JoinColumn(name = "crust_name")
    )
    private List<Crust> crusts;


    public ViewMenu(String pid, int price) {
        this.pid = pid;
        this.price = price;
    }
    public ViewMenu(String pid) {
        this.pid = pid;
    }

    public String getpid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public ViewMenu() {
    }
}
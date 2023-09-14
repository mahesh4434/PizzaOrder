package com.pizzaDeliveryApp.pizzaApi.entity;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class GroupedOrder {
    @Id
    private String groupId;
    @ElementCollection
    private List<Integer> orderIds;
}

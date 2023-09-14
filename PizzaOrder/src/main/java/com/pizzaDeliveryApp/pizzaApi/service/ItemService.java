package com.pizzaDeliveryApp.pizzaApi.service;

import com.pizzaDeliveryApp.pizzaApi.entity.Item;
import com.pizzaDeliveryApp.pizzaApi.exception.ItemException;


public interface ItemService {

    public Item addItem(Item item)throws ItemException;


}
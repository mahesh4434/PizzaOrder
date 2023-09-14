package com.pizzaDeliveryApp.pizzaApi.constant;

public class QueryConstants {
    public static final String FIND_ALL_ITEMS_NOT_NULL = "SELECT i FROM Item i WHERE i IS NOT NULL";
    public static final String JOIN_FETCH_CRUSTS ="SELECT vm FROM ViewMenu vm JOIN FETCH vm.crusts";
    public static final String JOIN_FETCH_SIZES ="SELECT vm FROM ViewMenu vm JOIN FETCH vm.sizes" ;
    public static final String JOIN_FETCH_TOPPINGS = "SELECT vm FROM ViewMenu vm JOIN FETCH vm.toppings";

}

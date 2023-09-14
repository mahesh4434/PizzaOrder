package com.pizzaDeliveryApp.pizzaApi.repository;

import com.pizzaDeliveryApp.pizzaApi.entity.GroupedOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupedOrderRepository extends JpaRepository<GroupedOrder, String> {

}

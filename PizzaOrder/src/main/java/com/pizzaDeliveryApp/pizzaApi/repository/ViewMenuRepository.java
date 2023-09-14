package com.pizzaDeliveryApp.pizzaApi.repository;

import com.pizzaDeliveryApp.pizzaApi.entity.ViewMenu;
import com.pizzaDeliveryApp.pizzaApi.constant.QueryConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface ViewMenuRepository extends JpaRepository<ViewMenu, String> {
    @Query(QueryConstants.JOIN_FETCH_TOPPINGS)
    List<ViewMenu> findAllWithPizzas();
    @Query(QueryConstants.JOIN_FETCH_SIZES)
    List<ViewMenu> findAllWithSizes();
}




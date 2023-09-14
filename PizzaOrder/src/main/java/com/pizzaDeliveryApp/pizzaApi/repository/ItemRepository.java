package com.pizzaDeliveryApp.pizzaApi.repository;



import com.pizzaDeliveryApp.pizzaApi.entity.Item;
import com.pizzaDeliveryApp.pizzaApi.constant.QueryConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    // add query in constant package
    @Query(QueryConstants.FIND_ALL_ITEMS_NOT_NULL)
    List<Item> findAllNotNull();
}

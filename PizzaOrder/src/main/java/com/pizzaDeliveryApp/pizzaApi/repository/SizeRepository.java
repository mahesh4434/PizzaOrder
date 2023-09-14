package com.pizzaDeliveryApp.pizzaApi.repository;

import com.pizzaDeliveryApp.pizzaApi.entity.Size;
import com.pizzaDeliveryApp.pizzaApi.constant.QueryConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@EnableJpaRepositories
@Repository
public interface SizeRepository extends JpaRepository<Size, String> {

    @Query(QueryConstants.JOIN_FETCH_SIZES)
    List<Size> findAllWithSizes();
}

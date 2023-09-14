package com.pizzaDeliveryApp.pizzaApi.repository;

import com.pizzaDeliveryApp.pizzaApi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findOneByEmailIdAndPassword(String username, String password);
    User findByEmailId(String emailId);
}

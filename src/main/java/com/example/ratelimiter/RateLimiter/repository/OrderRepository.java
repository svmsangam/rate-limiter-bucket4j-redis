package com.example.ratelimiter.RateLimiter.repository;

import com.example.ratelimiter.RateLimiter.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findById(Long id);
}

package com.example.ratelimiter.RateLimiter.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
@Data
public class Order extends AbstractEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Column
    private String name;

    @Column
    private int quantity;

    @Column
    private double amount;
}

package com.example.ratelimiter.RateLimiter.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDTO {
    private Long id;

    private Date createdDate;

    private String name;

    private int quantity;

    private double amount;
}

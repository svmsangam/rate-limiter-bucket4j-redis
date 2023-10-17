package com.example.ratelimiter.RateLimiter.service.impl;

import com.example.ratelimiter.RateLimiter.converter.OrderConverter;
import com.example.ratelimiter.RateLimiter.dto.RestResponseDto;
import com.example.ratelimiter.RateLimiter.repository.OrderRepository;
import com.example.ratelimiter.RateLimiter.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderConverter orderConverter;

    private final OrderRepository orderRepository;

    @Override
    public RestResponseDto list() {
        return RestResponseDto.INSTANCE()
                .success().detail(orderConverter.convertToDtoList(orderRepository.findAll()));
    }
}

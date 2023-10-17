package com.example.ratelimiter.RateLimiter.controller;

import com.example.ratelimiter.RateLimiter.annotations.Limit;
import com.example.ratelimiter.RateLimiter.dto.RestResponseDto;
import com.example.ratelimiter.RateLimiter.service.LimitService;
import com.example.ratelimiter.RateLimiter.service.OrderService;
import io.github.bucket4j.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;
    private final LimitService limitService;

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<RestResponseDto> listOrders(){
        Bucket bucket = limitService.resolveBucket("order");
        RestResponseDto restResponseDto = new RestResponseDto();
        restResponseDto.setMessage("Order Limit Reached");
        if(bucket.tryConsume(1)) {
            return ResponseEntity.ok(orderService.list());
        }else {
           return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(restResponseDto);
        }
    }
}

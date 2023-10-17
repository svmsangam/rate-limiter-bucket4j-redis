package com.example.ratelimiter.RateLimiter;

import com.example.ratelimiter.RateLimiter.entity.Order;
import com.example.ratelimiter.RateLimiter.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class Startup {
    private final OrderRepository orderRepository;
    private static final  String[] words = {"apple", "banana", "cherry", "date", "grape", "kiwi", "lemon", "orange", "pear", "strawberry"};



    @PostConstruct
    void initialize(){
        orderSetup();
    }

    public void orderSetup(){
        Random random = new Random();
        List<Order> orderList = new ArrayList<>();
        List<String> wordList = Arrays.asList(words);
        if(orderRepository.findAll().size() == 0) {
            for (int i = 0; i < 10; i++) {
                Collections.shuffle(wordList);
                int rand_int = random.nextInt(600);
                Order order = new Order();
                order.setName(wordList.stream().findFirst().orElse("tomato"));
                order.setAmount(rand_int);
                order.setQuantity(i + random.nextInt(6));
                orderList.add(order);
            }

            orderRepository.saveAll(orderList);
        }
    }
}

package com.example.ratelimiter.RateLimiter.converter;

import com.example.ratelimiter.RateLimiter.dto.OrderDTO;
import com.example.ratelimiter.RateLimiter.entity.Order;
import com.example.ratelimiter.RateLimiter.util.IConvertable;
import com.example.ratelimiter.RateLimiter.util.IListConvertable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverter implements IConvertable<Order, OrderDTO>, IListConvertable<Order,OrderDTO> {
    @Override
    public Order convertToEntity(OrderDTO dto) {
        return this.copyConvertToEntity(dto,new Order());
    }

    @Override
    public OrderDTO convertToDto(Order entity) {
        if(entity == null){
            return null;
        }
        OrderDTO dto = new OrderDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setQuantity(entity.getQuantity());
        dto.setCreatedDate(entity.getCreated());
        dto.setAmount(entity.getAmount());
        return dto;
    }

    @Override
    public Order copyConvertToEntity(OrderDTO dto, Order entity) {
        if(entity == null || dto == null){
            return entity;
        }
        entity.setName(dto.getName());
        entity.setQuantity(dto.getQuantity());
        entity.setAmount(dto.getAmount());

        return entity;
    }

    @Override
    public List<OrderDTO> convertToDtoList(List<Order> entities) {
        return entities.parallelStream().map(this :: convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<Order> convertToEntityList(List<OrderDTO> dtoList) {
        return null;
    }
}

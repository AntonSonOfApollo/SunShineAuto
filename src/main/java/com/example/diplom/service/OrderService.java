package com.example.diplom.service;

import com.example.diplom.entity.Order;
import com.example.diplom.entity.Owner;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface OrderService {
    Iterable<Order> findAll();

    Optional<Order> findById(Integer id);

    Optional<Order> save(Order order);

    Optional<Order> deleteById(Integer id);

    Optional<Order> update(Order order);
}

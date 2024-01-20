package com.example.diplom.rdb;


import com.example.diplom.entity.Order;
import com.example.diplom.entity.Owner;
import com.example.diplom.rdb.repository.OrderRepository;
import com.example.diplom.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RdbOrderService implements OrderService {

    public final OrderRepository orderRepository;

    public RdbOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<Order> save(Order order) {
        return Optional.of(orderRepository.save(order));
    }

    @Override
    public Optional<Order> deleteById(Integer id) {
        Optional<Order> deleted = findById(id);
        if (deleted.isPresent()) {
            orderRepository.deleteById(id);
        }
        return deleted;
    }

    @Override
    public Optional<Order> update(Order order) {
        Optional<Order> updated = findById(order.getId());
        if (updated.isPresent()) {
            updated = Optional.of(orderRepository.save(order));
        }
        return updated;
    }


}

package com.example.diplom.service;

import com.example.diplom.entity.Car;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CarService {

    Iterable<Car> findAll();

    Optional<Car> findById(Integer id);

    Optional<Car> save(Car car);

    Optional<Car> deleteById(Integer id);

    Optional<Car> update(Car car);
}

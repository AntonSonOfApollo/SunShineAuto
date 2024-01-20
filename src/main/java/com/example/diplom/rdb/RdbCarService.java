package com.example.diplom.rdb;

import com.example.diplom.entity.Car;
import com.example.diplom.rdb.repository.CarRepository;
import com.example.diplom.service.CarService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RdbCarService implements CarService {

private final CarRepository carRepository;

public RdbCarService(CarRepository carRepository) {
    this.carRepository = carRepository;
}

    @Override
    public Iterable<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> findById(Integer id) {
        return carRepository.findById(id);
    }

    @Override
    public Optional<Car> save(Car car) {
        return Optional.of(carRepository.save(car));
    }

    @Override
    public Optional<Car> deleteById(Integer id) {
        Optional<Car> deleted = findById(id);
        if (deleted.isPresent()) {
            carRepository.deleteById(id);
        }
        return deleted;
    }

    @Override
    public Optional<Car> update(Car car) {
        Optional<Car> updated = findById(car.getId());
        if (updated.isPresent()) {
            updated = Optional.of(carRepository.save(car));
        }
        return updated;
    }

}

package com.carservice.services;

import com.carservice.models.Car;
import com.carservice.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> cars() {
        return carRepository.findAll();
    }

    public Car show(long id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car save(Car car) {
        Car newCar = carRepository.save(car);
        return newCar;
    }

    public List<Car> getCarsByUserId(long id) {
        return carRepository.findByUserId(id);
    }

}

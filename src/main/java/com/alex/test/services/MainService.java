package com.alex.test.services;

import com.alex.test.model.Car;
import com.alex.test.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getCar() {
        return carRepository.findAll();
    }
}

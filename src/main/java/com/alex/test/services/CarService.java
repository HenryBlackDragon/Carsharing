package com.alex.test.services;

import com.alex.test.constans.Constants;
import com.alex.test.model.Car;
import com.alex.test.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;


    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> getAllActiveCars() {
        return getAllCars().stream().filter(Car::isActive).collect(Collectors.toList());
    }

    public List<Car> getAllNotActiveCars() {
        return getAllCars().stream().filter(car -> !car.isActive()).collect(Collectors.toList());
    }

    public Car getCarById(Long id) {
        return carRepository.findOne(id);
    }

    public void activateOrRefuseCar(Long id, String value) {
        Car car = carRepository.findOne(id);

        if (Constants.ACTIVATE_CAR.equals(value)) {
            car.setActive(true);
            carRepository.save(car);

        } else if (Constants.REFUSE_CAR.equals(value)) {
            car.setUserCar(null);
            carRepository.delete(car);

        }
    }

    public void deleteAd(Long id) {
        Car car = carRepository.findOne(id);

        car.setUserCar(null);

        carRepository.delete(car);
    }
}

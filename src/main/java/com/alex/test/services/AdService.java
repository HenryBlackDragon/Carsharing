package com.alex.test.services;

import com.alex.test.model.Car;
import com.alex.test.model.User;
import com.alex.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdService {

    @Autowired
    private UserRepository userRepository;

    public User createNewAd(String email, Car car) {
        User userByEmail = userRepository.findUserByEmail(email);

        car.setUserCar(userByEmail);
        userByEmail.getCars().add(car);


//        if (CollectionUtils.isEmpty(userByEmail.getCars())) {
//
//
//            cars = new HashSet<>();
//            cars.add(Car.builder()
//                    .mark(car.getMark())
//                    .model(car.getModel())
//                    .releaseYear(car.getReleaseYear())
//                    .stateNum(car.getStateNum())
//                    .mileage(car.getMileage())
//                    .countOfSeats(car.getCountOfSeats())
//                    .boxType(car.getBoxType())
//                    .bodyType(car.getBodyType())
//                    .gear(car.getGear())
//                    .engine(car.getEngine())
//                    .fuelConsumption(car.getFuelConsumption())
//                    .dataAboutCar(car.getDataAboutCar())
//                    .carDamages(car.getCarDamages())
//                    .insuranceInformation(car.getInsuranceInformation())
//                    .price(car.getPrice())
//                    .typePrice(car.getTypePrice())
//                    .text(car.getText())
//                    .photo(car.getPhoto())
//                    .userCar(userByEmail)
//                    .build());
//        } else {
//            cars.add(car);
//        }

//        userByEmail.setCars(cars);
        userRepository.save(userByEmail);

        return userByEmail;
    }
}

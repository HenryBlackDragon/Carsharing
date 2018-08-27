//package com.alex.test.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "cars")
//templates class Car {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "cars_id")
//    private Long id;
//
//    @Column(name = "mark")
//    private String mark;
//
//    @Column(name = "model")
//    private String model;
//
//    @Column(name = "release_year")
//    private Integer releaseYear;
//
//    @Column(name = "state_num")
//    private String stateNum;
//
//    @Column(name = "mileage")
//    private Integer mileage;
//
//    @Column(name = "count_seats")
//    private Integer countOfSeats;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "base_latlng_id", referencedColumnName = "base_latlng_id")
//    private BaseLatLng latLng;
//
//    @Column(name = "box_type")
//    private String boxType;
//
//    @Column(name = "body_type")
//    private String bodyType;
//
//    @Column(name = "gear")
//    private String gear;
//
//    @Column(name = "engine")
//    private String engine;
//
//    @Column(name = "fuel_type")
//    private String fuelType;
//
//    @Column(name = "fuel_consumption")
//    private Integer fuelConsumption;
//
//    @Column(name = "data_about_car")
//    private String dataAboutCar;
//
//    @Column(name = "car_damages")
//    private String carDamages;
//
//    @Column(name = " insurance_information")
//    private String insuranceInformation;
//
//    @Column(name = "price")
//    private Integer price;
//
//    @Column(name = "text")
//    private String text;
//
//    @OneToMany(mappedBy = "photoCars",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    protected Set<Photo> photos;
//
//    // TODO: 20
//}
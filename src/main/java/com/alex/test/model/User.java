package com.alex.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    //    @Column(name = "passport_id")
    @OneToOne
    @PrimaryKeyJoinColumn
    private DataPassport passportId;

    //    @Column(name = "drivers_license_id")
    @OneToOne
    @PrimaryKeyJoinColumn
    private DrivingLicense license;

    @Column(name = "photo")
    private String photo;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany
    @JoinTable(name = "user_cars", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private Set<Car> cars;
}

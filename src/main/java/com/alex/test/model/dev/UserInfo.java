//package com.alex.test.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "users_info")
//public class UsersInfo {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_info_id")
//    private Long id;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "passport_id", referencedColumnName = "passport_id")
//    private DataPassport dataPassport;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "driving_license_id", referencedColumnName = "driving_license_id")
//    private DrivingLicense license;
//
//    @Column(name = "photo")
//    private String photo;
//
//    @Column(name = "phone_number")
//    private String phoneNumber;
//}

//package com.alex.test.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "driving_license")
//templates class DrivingLicense {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "driving_license_id")
//    private Long id;
//
//    @Column(name = "series")
//    private String series;
//
//    @Column(name = "num_license")
//    private Integer numLicense;
//
//    @Column(name = "date_issued")
//    private LocalDate dateIssued;
//
//    @Column(name = "organization_issued")
//    private String organizationIssued;
//
//    @Column(name = "date_end")
//    private LocalDate dateEnd;
//
//    @Column(name = "category")
//    private String category;
//
//}

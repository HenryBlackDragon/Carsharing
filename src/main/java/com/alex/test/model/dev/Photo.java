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
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "photos")
//templates class Photo {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(name = "path")
//    private String path;
//
////, referencedColumnName = "cars_id"
//    @ManyToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "cars_id")
//    private Cars photoCars;
//}

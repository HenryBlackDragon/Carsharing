package com.alex.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "passport_data")
public class DataPassport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "series")
    private String series;

    @Column(name = "num_passport")
    private Integer numPassport;

    @Column(name = "date_issued")
    private LocalDate dateIssued;

    @Column(name = "organization_issued")
    private String organizationIssued;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @Column(name = "date_born")
    private LocalDate dateBorn;

    @Column(name = "place_born")
    private String placeBorn;

    @OneToOne(mappedBy = "passport_data")
    private User user;

}

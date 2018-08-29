package com.alex.test.model;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cars_id")
    private Long id;

    @NotBlank(message = "Укажите марку")
    private String mark;

    @NotBlank(message = "Укажите модель")
    private String model;

    @NotNull(message = "Укажите год выпуска")
    private Integer releaseYear;

    @NotBlank(message = "Укажите гос. номер")
    private String stateNum;

    private Integer mileage;

    private Integer countOfSeats;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "base_latlng_id", referencedColumnName = "base_latlng_id")
//    private BaseLatLng latLng;

    private String boxType;

    private String bodyType;

    private String gear;

    @NotBlank(message = "Укажите тип")
    private String engine;

    @NotBlank(message = "Укажите тип топлива")
    private String fuelType;

    private Integer fuelConsumption;

    @Lob
    @Column(columnDefinition = "BLOB")
    private String dataAboutCar;

    @Lob
    @Column(columnDefinition = "BLOB")
    private String carDamages;

    @Lob
    @Column(columnDefinition = "BLOB")
    private String insuranceInformation;

    @NotNull(message = "Укажите цену")
    private Integer price;

    private String typePrice;

    @Lob
    @NotBlank(message = "Укажите текст обявления")
    @Column(columnDefinition = "BLOB")
    private String text;

    private String photo;

//    @OneToMany(mappedBy = "photoCars",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    protected Set<Photo> photos;

    // TODO: 20

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userCar;
}
package com.alex.test.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photo_id")
    private Long id;

    private String path;

    @ManyToOne
    @JoinColumn(name = "cars_id")
    private Car photoCars;

}

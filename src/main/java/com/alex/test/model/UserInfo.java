package com.alex.test.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_info_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userInfoPassport")
    private DataPassport dataPassport;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userInfoDriverLicense")
    private DrivingLicense license;

    @Column(name = "photo")
    private String photo;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

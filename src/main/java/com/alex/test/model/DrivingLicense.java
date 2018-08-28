package com.alex.test.model;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "driving_license")
public class DrivingLicense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "driving_license_id")
    private Long id;

    @NotBlank(message = "Укажите серию")
    private String series;

    @NotNull(message = "Укажите номер удостоверения")
    private Integer numLicense;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Укажите дату выдачи прав")
    private LocalDate dateIssued;

    @NotBlank(message = "Укажите организацию")
    private String organizationIssued;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Укажите дату окончания")
    private LocalDate dateEnd;

    @NotBlank(message = "Укажите категорию")
    private String category;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_info_id", nullable = false)
    private UserInfo userInfoDriverLicense;

}

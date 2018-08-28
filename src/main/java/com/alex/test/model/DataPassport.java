package com.alex.test.model;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "passport_data")
public class DataPassport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "passport_id")
    private Long id;

    @NotBlank(message = "Укажите фамилию")
    private String surname;

    @NotBlank(message = "Укажите имя")
    private String name;

    @NotBlank(message = "Укажите отчество")
    private String patronymic;

    @NotBlank(message = "Укажите серию паспорта")
    @Size(max = 2, message = "Серия не должна превышать более двух букв")
    private String series;

    @NotNull(message = "Укажите номер паспорта")
    private Integer numPassport;

    @NotBlank(message = "Укажите организацию")
    private String organizationIssued;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Укажите дату выдачи паспорта")
    private LocalDate dateIssued;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Укажите дату рождения")
    private LocalDate dateBorn;

    @NotBlank(message = "Укажите место рождения")
    private String placeBorn;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_info_id", nullable = false)
    private UserInfo userInfoPassport;
}

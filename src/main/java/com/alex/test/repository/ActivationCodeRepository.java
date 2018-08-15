package com.alex.test.repository;

import com.alex.test.model.ActivationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationCodeRepository extends JpaRepository<ActivationCode, Long> {
    ActivationCode findActivationCodeByCode(String code);
}

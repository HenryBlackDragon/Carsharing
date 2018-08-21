package com.alex.test.repository;

import com.alex.test.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

//    UserInfo findUserInfoBy

}

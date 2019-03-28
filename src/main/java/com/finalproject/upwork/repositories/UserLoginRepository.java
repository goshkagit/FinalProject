package com.finalproject.upwork.repositories;

import com.finalproject.upwork.models.UserLoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserLoginRepository extends JpaRepository<UserLoginModel , Long> {

    UserLoginModel findByNickname(String nickname);

}

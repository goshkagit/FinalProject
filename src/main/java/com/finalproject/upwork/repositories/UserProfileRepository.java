package com.finalproject.upwork.repositories;

import com.finalproject.upwork.models.UserProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;


@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileModel , Long> {

    List<UserProfileModel> findAllBySkill(String skill);

}

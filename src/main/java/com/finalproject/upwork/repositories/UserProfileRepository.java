package com.finalproject.upwork.repositories;

import com.finalproject.upwork.models.enums.Type;
import com.finalproject.upwork.models.UserProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileModel, Long> {

    List<UserProfileModel> findAllBySkill(Type skill);

    List<UserProfileModel> findAllByName(String name);

    List<UserProfileModel> findAllByNameAndSkill(String name, Type skill);


}

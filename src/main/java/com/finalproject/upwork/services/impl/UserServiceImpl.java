package com.finalproject.upwork.services.impl;


import com.finalproject.upwork.models.UserLoginModel;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.repositories.UserLoginRepository;
import com.finalproject.upwork.repositories.UserProfileRepository;
import com.finalproject.upwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserLoginRepository userLoginRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public void addUser(UserLoginModel userLoginModel) {
           userLoginRepository.save(userLoginModel);
    }

    @Override
    public void addUserProfileDetails(UserProfileModel userProfileModel) {
        userProfileRepository.save(userProfileModel);
    }

    @Override
    public UserProfileModel getById(long id) {
        return userProfileRepository.findById(id).get();
    }

    @Override
    public List<UserProfileModel> whereSkillISJava(String skill) {
         return userProfileRepository.findAllBySkill("Java");
    }


}

package com.finalproject.upwork.services.impl;


import com.finalproject.upwork.models.UserLoginModel;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.repositories.UserLoginRepository;
import com.finalproject.upwork.repositories.UserProfileRepository;
import com.finalproject.upwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void addUser(UserLoginModel userLoginModel) {

        String password = userLoginModel.getPassword();

        userLoginModel.setPassword(passwordEncoder.encode(password));

        userLoginRepository.save(userLoginModel);
    }

    @Override
    public void addUserProfileDetails(UserProfileModel userProfileModel , UserLoginModel userLoginModel) {

        userProfileModel.setUser_id(userLoginModel);
        userProfileRepository.save(userProfileModel);
    }

    @Override
    public UserProfileModel getProfileById(long id) {
        return userProfileRepository.findById(id).get();
    }

    @Override
    public List<UserProfileModel> whereSkillISJava(String skill) {
         return userProfileRepository.findAllBySkill("Java");
    }

    @Override
    public UserLoginModel findByNickname(String nickname) {
        return userLoginRepository.findByNickname(nickname);
    }

    @Override
    public UserLoginModel getLoginById(long id) {
        return userLoginRepository.findById(id).get();
    }


}

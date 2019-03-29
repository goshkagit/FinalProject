package com.finalproject.upwork.services.impl;


import com.finalproject.upwork.models.DTO.UserProfileModelDTO;
import com.finalproject.upwork.models.Type;
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

    @Autowired
    private UserService userService;


    @Override
    public void addUser(UserLoginModel userLoginModel) {

        String password = userLoginModel.getPassword();

        userLoginModel.setPassword(passwordEncoder.encode(password));

        userLoginRepository.save(userLoginModel);
    }

    @Override
    public void addUserProfileDetails(UserProfileModel userProfileModel , UserProfileModelDTO dto , long id ) {

        userProfileModel.setSkill(Type.valueOf(dto.getSkill().toUpperCase()));

        userProfileModel.setUser_id(userService.getLoginById(id));

        userProfileRepository.save(userProfileModel);
    }

    @Override
    public UserProfileModel getProfileById(long id) {
        return userProfileRepository.findById(id).get();
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

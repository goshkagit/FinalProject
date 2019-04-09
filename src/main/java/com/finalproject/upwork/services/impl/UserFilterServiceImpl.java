package com.finalproject.upwork.services.impl;


import com.finalproject.upwork.models.DTO.GetDTO.GetUserLoginDTO;
import com.finalproject.upwork.models.UserLoginModel;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.models.enums.Type;
import com.finalproject.upwork.repositories.UserLoginRepository;
import com.finalproject.upwork.repositories.UserProfileRepository;
import com.finalproject.upwork.services.UserFilterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserFilterServiceImpl implements UserFilterService {


    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Override
    public List<UserProfileModel> whereSkillIs(String skill) {

        return userProfileRepository.findAllBySkill(Type.valueOf(skill.toUpperCase()));

    }

    @Override
    public List<UserProfileModel> whereNameIs(String name) {
        return userProfileRepository.findAllByName(name);
    }

    @Override
    public GetUserLoginDTO whereNicknameIs(String nickname) {

        UserLoginModel model = userLoginRepository.findByNickname(nickname);

        return modelMapper.map(model, GetUserLoginDTO.class);
    }

    @Override
    public List<UserProfileModel> whereNameAndSkillIs(String name, String skill) {
        return userProfileRepository.findAllByNameAndSkill(name, Type.valueOf(skill.toUpperCase()));
    }

    @Override
    public UserProfileModel whereUserIdIs(long loginId) {
        UserLoginModel userLoginModel = userLoginRepository.findById(loginId).orElse(null);

        return userProfileRepository.findByUserId(userLoginModel);
    }

    @Override
    public List<UserProfileModel> getAllUsers() {
        return userProfileRepository.findAll();
    }


}

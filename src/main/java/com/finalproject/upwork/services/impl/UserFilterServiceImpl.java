package com.finalproject.upwork.services.impl;


import com.finalproject.upwork.models.Type;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.repositories.UserProfileRepository;
import com.finalproject.upwork.services.UserFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserFilterServiceImpl implements UserFilterService {


    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public List<UserProfileModel> whereSkillIs(String skill) {

        return userProfileRepository.findAllBySkill(Type.valueOf(skill.toUpperCase()));

        }




}

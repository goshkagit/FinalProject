package com.finalproject.upwork.services;

import com.finalproject.upwork.models.UserProfileModel;

import java.util.List;

public interface UserFilterService {

    List<UserProfileModel> whereSkillIs(String skill);

}

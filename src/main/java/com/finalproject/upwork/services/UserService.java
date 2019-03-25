package com.finalproject.upwork.services;


import com.finalproject.upwork.models.UserLoginModel;
import com.finalproject.upwork.models.UserProfileModel;

import java.util.List;


public interface UserService {

    void addUser(UserLoginModel userLoginModel);
    void addUserProfileDetails(UserProfileModel userProfileModel);
    UserProfileModel getById(long id);
    List<UserProfileModel> whereSkillISJava(String skill);

}




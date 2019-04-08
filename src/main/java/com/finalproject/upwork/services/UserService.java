package com.finalproject.upwork.services;


import com.finalproject.upwork.models.UserLoginModel;
import com.finalproject.upwork.models.UserProfileModel;


public interface UserService {

    void addUser(UserLoginModel userLoginModel);

    void addUserProfileDetails(UserProfileModel userProfileModel, long id);

    UserLoginModel getLoginById(long id);

    UserProfileModel getProfileById(long id);

    UserLoginModel findByNickname(String nickname);

    void updateProfile(UserProfileModel userProfileModel, long id);

    void deleteUser(long loginId, long profileId);
}




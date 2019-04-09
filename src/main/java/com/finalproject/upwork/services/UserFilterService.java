package com.finalproject.upwork.services;

import com.finalproject.upwork.models.DTO.GetDTO.GetUserLoginDTO;
import com.finalproject.upwork.models.UserProfileModel;

import java.util.List;

public interface UserFilterService {

    List<UserProfileModel> whereSkillIs(String skill);

    List<UserProfileModel> whereNameIs(String name);

    GetUserLoginDTO whereNicknameIs(String nickname);

    List<UserProfileModel> whereNameAndSkillIs(String name, String skill);

    UserProfileModel whereUserIdIs(long id);
}

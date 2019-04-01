package com.finalproject.upwork.services.impl;


import com.finalproject.upwork.models.UserLoginModel;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.models.enums.Type;
import com.finalproject.upwork.repositories.SubmittedRepository;
import com.finalproject.upwork.repositories.TaskRepository;
import com.finalproject.upwork.repositories.UserLoginRepository;
import com.finalproject.upwork.repositories.UserProfileRepository;
import com.finalproject.upwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubmittedRepository submittedRepository;

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
    public void addUserProfileDetails(UserProfileModel userProfileModel, long userId) {


        userProfileModel.setUserId(userService.getLoginById(userId));

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
    public void updateProfile(UserProfileModel userProfileModel, long userId) {

        UserProfileModel profileModel = userProfileRepository.findById(userId).get();

        profileModel.setName(userProfileModel.getName());
        profileModel.setSurname(userProfileModel.getSurname());
        profileModel.setEmail(userProfileModel.getEmail());
        profileModel.setPortfolio(userProfileModel.getPortfolio());
        profileModel.setSkill(userProfileModel.getSkill());

        userProfileRepository.save(profileModel);
    }

    @Override
    public void deleteUser(long id) {

        UserLoginModel loginModel = userLoginRepository.findById(id).get();
        UserProfileModel profileModel = userProfileRepository.findById(id).get();

        submittedRepository.findAllBySubmittedUsersId(profileModel).forEach(submittedModel -> submittedRepository.delete(submittedModel));

        taskRepository.findAllByWhoPosted(profileModel).forEach(taskModel -> taskRepository.delete(taskModel));

        userProfileRepository.delete(profileModel);

        userLoginRepository.delete(loginModel);

    }

    @Override
    public UserLoginModel getLoginById(long loginId) {
        return userLoginRepository.findById(loginId).get();
    }


}

package com.finalproject.upwork.services.impl;


import com.finalproject.upwork.exception.NotFoundException;
import com.finalproject.upwork.exception.SpecialCharsException;
import com.finalproject.upwork.models.UserLoginModel;
import com.finalproject.upwork.models.UserPrincipal;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.models.enums.Roles;
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

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void addUser(UserLoginModel userLoginModel) {

        String password = userLoginModel.getPassword();

        userLoginModel.setRole(Roles.ROLE_USER);

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
        return userProfileRepository.findById(id).orElse(null);
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
    public void deleteUser(long loginId, long profileId) {

        UserLoginModel loginModel = userLoginRepository.findById(loginId).orElse(null);

        UserProfileModel profileModel = userProfileRepository.findById(profileId).orElse(null);

        if (loginModel == null) {
            throw new NotFoundException("There is no user with login id : " + loginId);
        } else if (profileModel == null) {
            throw new NotFoundException("There is no user with profile id :" + profileId);
        }
        submittedRepository.findAllBySubmittedUsersId(profileModel).forEach(submittedModel -> submittedRepository.delete(submittedModel));

        taskRepository.findAllByWhoPosted(profileModel).forEach(taskModel -> taskRepository.delete(taskModel));

        userProfileRepository.delete(profileModel);

        userLoginRepository.delete(loginModel);

    }

    @Override
    public void grandAdmin(String nickname) {
        UserLoginModel userLoginModel = userLoginRepository.findByNickname(nickname);

        if (userLoginModel == null) {
            throw new NotFoundException("There is no user with nickname : " + nickname);
        }
        userLoginModel.setRole(Roles.ROLE_ADMIN);
        userLoginRepository.save(userLoginModel);
        userDetailsService.loadUserByUsername(nickname);
    }

    @Override
    public UserLoginModel getLoginById(long loginId) {
        return userLoginRepository.findById(loginId).orElse(null);
    }


}

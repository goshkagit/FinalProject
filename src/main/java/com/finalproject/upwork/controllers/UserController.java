package com.finalproject.upwork.controllers;


import com.finalproject.upwork.exception.NotFoundException;
import com.finalproject.upwork.models.DTO.UserLoginDTO;
import com.finalproject.upwork.models.DTO.UserProfileModelDTO;
import com.finalproject.upwork.models.UserLoginModel;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.services.UserService;
import com.finalproject.upwork.services.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;


@RestController
public class UserController {

    @Autowired
    private ModelMapper profileModelMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;


    @PostMapping("/addUser")
    public ResponseEntity addUser(@Valid @RequestBody UserLoginDTO userLoginModelDTO) {

        UserLoginModel userLoginModel = modelMapper.map(userLoginModelDTO, UserLoginModel.class);

        userService.addUser(userLoginModel);

        return ResponseEntity.ok("Sign up successfully");
    }

    @PostMapping("/addUserDetails/{loginId}")
    public ResponseEntity addUser(@Valid @RequestBody UserProfileModelDTO userProfileModelDTO, @PathVariable long loginId) {

        UserProfileModel userProfileModel = profileModelMapper.map(userProfileModelDTO, UserProfileModel.class);

        userService.addUserProfileDetails(userProfileModel, loginId);

        return ResponseEntity.ok("Details added successfully");
    }

    @GetMapping("/getByID/{profileId}")
    public ResponseEntity getUser(@PathVariable long profileId) {
        UserProfileModel profile = userService.getProfileById(profileId);
        if (profile == null) {
            throw new NotFoundException("There is no user with id :" + profileId);
        }
            return ResponseEntity.ok(profile);
    }

    @PutMapping("/updateProfile/{profileId}")
    public ResponseEntity updateUser(@Valid @RequestBody UserProfileModelDTO userProfileModelDTO, @PathVariable long profileId) {

        UserProfileModel userProfileModel = profileModelMapper.map(userProfileModelDTO, UserProfileModel.class);

        if (userProfileModel == null) {
            throw new NotFoundException("There is no user with id :" + profileId);
        }
        userService.updateProfile(userProfileModel, profileId);

        return ResponseEntity.ok("Updated successfully");
    }


    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity deleteUser(@PathVariable long userId) {

        userService.deleteUser(userId);

        return ResponseEntity.ok("User deleted successfully");
    }

}

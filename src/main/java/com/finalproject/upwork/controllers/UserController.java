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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    private ModelMapper profileDTOModelMapper;

    @Autowired
    private UserService userService;


    @PostMapping("/addUser")
    public ResponseEntity addUser(@Valid @RequestBody UserLoginDTO userLoginModelDTO) {

        UserLoginModel userLoginModel = modelMapper.map(userLoginModelDTO, UserLoginModel.class);

        userService.addUser(userLoginModel);

        return ResponseEntity.status(HttpStatus.CREATED).body("Sign up successfully");
    }

    @PostMapping("/addUserDetails/{loginId}")
    public ResponseEntity addUser(@Valid @RequestBody UserProfileModelDTO userProfileModelDTO, @PathVariable long loginId) {

        UserProfileModel userProfileModel = profileModelMapper.map(userProfileModelDTO, UserProfileModel.class);

        userService.addUserProfileDetails(userProfileModel, loginId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Details added successfully");
    }

    @Secured(value = "ROLE_USER")
    @GetMapping(value = "/getById/{profileId}")
    public ResponseEntity getUser(@PathVariable long profileId) {
        UserProfileModel profile = userService.getProfileById(profileId);
        if (profile == null) {
            throw new NotFoundException("There is no user with loginId :" + profileId);
        }
        UserProfileModelDTO dto = profileDTOModelMapper.map(profile, UserProfileModelDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Secured(value = "ROLE_USER")
    @PutMapping("/updateProfile/{profileId}")
    public ResponseEntity updateUser(@Valid @RequestBody UserProfileModelDTO userProfileModelDTO, @PathVariable long profileId) {

        UserProfileModel userProfileModel = profileModelMapper.map(userProfileModelDTO, UserProfileModel.class);

        if (userProfileModel == null) {
            throw new NotFoundException("There is no user with loginId :" + profileId);
        }
        userService.updateProfile(userProfileModel, profileId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Updated successfully");
    }


    @Secured(value = "ROLE_USER")
    @DeleteMapping("/deleteUser/{loginId}/{profileId}")
    public ResponseEntity deleteUser(@PathVariable long loginId, @PathVariable long profileId) {

        userService.deleteUser(loginId, profileId);

        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }

}

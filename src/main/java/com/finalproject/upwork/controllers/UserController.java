package com.finalproject.upwork.controllers;


import com.finalproject.upwork.models.DTO.UserLoginDTO;
import com.finalproject.upwork.models.DTO.UserProfileModelDTO;
import com.finalproject.upwork.models.UserLoginModel;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity addUser(@Valid @RequestBody UserLoginDTO userLoginModelDTO ){
           UserLoginModel userLoginModel = modelMapper.map(userLoginModelDTO , UserLoginModel.class);
           userService.addUser(userLoginModel);
           return ResponseEntity.ok("Sign up successfully");
    }

    @PostMapping("/addUserDetails")
    public ResponseEntity addUser(@Valid @RequestBody UserProfileModelDTO userProfileModelDTO){
        UserProfileModel userProfileModel = modelMapper.map(userProfileModelDTO , UserProfileModel.class);
        userService.addUserProfileDetails(userProfileModel);
        return ResponseEntity.ok("Details added successfully");
    }

//    @GetMapping("/getByID/{id}")
//    public ResponseEntity GetUser(@PathVariable long id) throws IOException {
//            return ResponseEntity.ok(  userServiceimpl.getById(id).toString() );
//    }

}

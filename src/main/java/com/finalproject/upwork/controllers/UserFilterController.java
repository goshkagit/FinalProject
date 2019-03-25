package com.finalproject.upwork.controllers;
import com.finalproject.upwork.models.DTO.UserProfileModelDTO;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class UserFilterController {


    @Autowired
    private UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/skillIsJava")
    public ResponseEntity addUser(){
        List<UserProfileModel> java = userService.whereSkillISJava("Java");

        List<UserProfileModelDTO> mappedList = java.stream()
                .map(userProfileModel -> modelMapper.map(userProfileModel, UserProfileModelDTO.class))
                .collect(Collectors.toList());


        return ResponseEntity.ok(mappedList);
}
}

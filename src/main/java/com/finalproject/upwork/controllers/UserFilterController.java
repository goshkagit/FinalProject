package com.finalproject.upwork.controllers;

import com.finalproject.upwork.exception.NotFoundException;
import com.finalproject.upwork.models.DTO.GetDTO.GetUserLoginDTO;
import com.finalproject.upwork.models.DTO.UserProfileModelDTO;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.services.UserFilterService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.stream.Collectors;


@RestController
@Secured(value = "ROLE_USER")
public class UserFilterController {


    @Autowired
    private UserFilterService userFilterService;

    @Autowired
    private ModelMapper profileDTOModelMapper;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/skillIs/{skill}")
    public ResponseEntity getAllBySkill(@PathVariable String skill) {

        List<UserProfileModel> all = userFilterService.whereSkillIs(skill);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no available users with skill :" + skill);
        }
        List<UserProfileModelDTO> allDTO = all
                .stream()
                .map(UserProfileModel -> profileDTOModelMapper.map(UserProfileModel, UserProfileModelDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(allDTO);
    }


    @GetMapping("/nameIs/{name}")
    public ResponseEntity getAllByName(@PathVariable String name) {

        List<UserProfileModel> all = userFilterService.whereNameIs(name);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no available users with name :" + name);
        }
        List<UserProfileModelDTO> allDTO = all
                .stream()
                .map(UserProfileModel -> profileDTOModelMapper.map(UserProfileModel, UserProfileModelDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(allDTO);
    }


    @GetMapping("/nameAndSkillIs/{name}/{skill}")
    public ResponseEntity getAllBySkill(@PathVariable String skill, @PathVariable String name) {

        List<UserProfileModel> all = userFilterService.whereNameAndSkillIs(name, skill);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no available users with name :" + name + " and skill: " + skill);
        }
        List<UserProfileModelDTO> allDTO = all
                .stream()
                .map(UserProfileModel -> profileDTOModelMapper.map(UserProfileModel, UserProfileModelDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(allDTO);
    }


    @GetMapping("/nicknameIs/{nickname}")
    public ResponseEntity getByNickname(@PathVariable String nickname) {

        GetUserLoginDTO user = userFilterService.whereNicknameIs(nickname);
        if (user == null) {
            throw new NotFoundException("There is no user with nickname :" + nickname);
        }

        GetUserLoginDTO getUserLoginDTO = modelMapper.map(user, GetUserLoginDTO.class);

        return ResponseEntity.ok(getUserLoginDTO);
    }
}

package com.finalproject.upwork.controllers;

import com.finalproject.upwork.exception.NotFoundException;
import com.finalproject.upwork.models.DTO.GetDTO.GetUserLoginDTO;
import com.finalproject.upwork.models.DTO.UserProfileModelDTO;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.services.UserFilterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
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
        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
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

        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
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

        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
    }


    @GetMapping("/nicknameIs/{nickname}")
    public ResponseEntity getByNickname(@PathVariable String nickname) {

        GetUserLoginDTO user = userFilterService.whereNicknameIs(nickname);
        if (user == null) {
            throw new NotFoundException("There is no user with nickname :" + nickname);
        }

        GetUserLoginDTO getUserLoginDTO = modelMapper.map(user, GetUserLoginDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body(getUserLoginDTO);
    }

    @GetMapping("/loginIdIs/{loginId}")
    public ResponseEntity getByNickname(@PathVariable long loginId) {

        UserProfileModel profileModel = userFilterService.whereUserIdIs(loginId);
        if (profileModel == null) {
            throw new NotFoundException("There is no user with loginId :" + loginId);
        }

        UserProfileModelDTO userProfileModelDTO = modelMapper.map(profileModel, UserProfileModelDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body(userProfileModelDTO);
    }

    @GetMapping("/allProfile")
    public ResponseEntity getAllProfiles() {

        List<UserProfileModel> all = userFilterService.getAllUsers();

        if (all == null) {
            throw new NotFoundException("There is no users yet");
        }

        List<UserProfileModelDTO> allDTO = all
                .stream()
                .map(UserProfileModel -> profileDTOModelMapper.map(UserProfileModel, UserProfileModelDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
    }
}

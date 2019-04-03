package com.finalproject.upwork.controllers;

import com.finalproject.upwork.exception.NotFoundException;
import com.finalproject.upwork.models.DTO.GetDTO.GetUserLoginDTO;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.services.UserFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserFilterController {


    @Autowired
    private UserFilterService userFilterService;


    @GetMapping("/skillIs/{skill}")
    public ResponseEntity getAllBySkill(@PathVariable String skill) {

        List<UserProfileModel> all = userFilterService.whereSkillIs(skill);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no available users with skill :" + skill);
        }

        return ResponseEntity.ok(all);
    }

    @GetMapping("/nameIs/{name}")
    public ResponseEntity getAllByName(@PathVariable String name) {

        List<UserProfileModel> all = userFilterService.whereNameIs(name);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no available users with name :" + name);
        }

        return ResponseEntity.ok(all);
    }

    @GetMapping("/nameAndSkillIs/{name}/{skill}")
    public ResponseEntity getAllBySkill(@PathVariable String skill , @PathVariable String name) {

        List<UserProfileModel> all = userFilterService.whereNameAndSkillIs(name ,skill);
        if (all.isEmpty()) {
            throw new NotFoundException("There is no available users with name :"+ name+" and skill: " + skill);
        }

        return ResponseEntity.ok(all);
    }

    @GetMapping("/nicknameIs/{nickname}")
    public ResponseEntity getByNickname(@PathVariable String nickname) {

       GetUserLoginDTO user = userFilterService.whereNicknameIs(nickname);
        if (user == null) {
            throw new NotFoundException("There is no user with nickname :"+ nickname);
        }
        return ResponseEntity.ok(user);
    }
}

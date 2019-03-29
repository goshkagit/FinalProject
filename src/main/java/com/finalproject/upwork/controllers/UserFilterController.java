package com.finalproject.upwork.controllers;
import com.finalproject.upwork.models.DTO.UserProfileModelDTO;
import com.finalproject.upwork.models.Type;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.services.UserFilterService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class UserFilterController {


    @Autowired
    private UserFilterService userFilterService;
//
//    @Autowired
//    ModelMapper modelMapper;

    @GetMapping("/skillIs/{skill}")
    public ResponseEntity getAllBySkill(@PathVariable String skill){

        List<UserProfileModel> all = userFilterService.whereSkillIs(skill);

        return ResponseEntity.ok(all);
}
}
